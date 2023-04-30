package com.booking.propertyservice.service.propertyservice;

import com.booking.commondomain.dto.property.*;
import com.booking.commondomain.dto.user.BookingUser;
import com.booking.bookingutils.exception.NotFoundException;
import com.booking.propertyservice.controller.request.PropertySearchCriteria;
import com.booking.propertyservice.integration.reservationservice.ReservationServiceIntegration;
import com.booking.propertyservice.integration.userservice.UserServiceIntegration;
import com.booking.propertyservice.mapper.PropertyMapper;
import com.booking.propertyservice.model.Property;
import com.booking.propertyservice.repository.PropertyRepository;
import com.booking.propertyservice.utils.ReactiveUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final ReservationServiceIntegration reservationServiceIntegration;
    private final UserServiceIntegration userServiceIntegration;
    private final PropertyServiceHelper propertyServiceHelper;
    private final ReactiveUtils reactiveUtils;
    private static final int PAGE_SIZE = 5;

    @Override
    public Mono<PageProperties> searchProperties(PropertySearchCriteria criteria) {
        log.info("Searching properties by location: {}, " +
                        "check-in date: {}, check-out date: {}, guest number: {}, and page number: {}.",
                criteria.getLocation(), criteria.getCheckIn(), criteria.getCheckOut(),
                criteria.getGuestNumber(), criteria.getCurrentPage());

        Pageable pageable = PageRequest.of(criteria.getCurrentPage(), PAGE_SIZE);
        PageProperties result = new PageProperties();

        return reactiveUtils.asyncMono(() -> propertyServiceHelper.getPropertyIds(criteria.getLocation(),
                        criteria.getCheckIn(), criteria.getCheckOut())
                        .flatMap(propertyIds -> {
                            List<Long> ids = propertyServiceHelper.preparePropertyIds(propertyIds);
                            int totalElements = propertyServiceHelper.countTotalElements(ids,
                                    criteria.getLocation(), criteria.getGuestNumber());
                            log.info("Found {} properties matching the search criteria.", totalElements);
                            result.setTotalElements(totalElements);
                            return propertyServiceHelper.getProperties(ids, criteria.getLocation(),
                                            criteria.getGuestNumber(), pageable)
                                    .doOnSuccess(properties -> log.info("Retrieved {} properties for page {}.",
                                            properties.size(), criteria.getCurrentPage()));
                        })
                        .map(properties -> {
                            result.setProperties(properties);
                            return result;
                        })
                        .doOnSuccess(res -> log.info("Returning a page of {} properties.", result.getProperties().size())));
    }

    @Override
    public Mono<PageProperties> getProperties(@AuthenticationPrincipal BookingUser user, int currentPage) {
        UUID userId = UUID.fromString(user.getId());
        log.info("Retrieving properties for user with id: {}", userId);

        Pageable pageable = PageRequest.of(currentPage, PAGE_SIZE);
        PageProperties result = new PageProperties();

        return reactiveUtils.asyncMono(() -> Mono.just(propertyRepository.count(user.getId()))
                .map(totalElements -> {
                    log.debug("Total number of properties found for user with id {} on page {} (page size={}): {}",
                            userId, currentPage, PAGE_SIZE, totalElements);

                    result.setTotalElements(totalElements);
                    return totalElements;
                })
                .flatMapMany(el -> Flux.fromIterable(propertyRepository.findByOwner(user.getId(), pageable)
                        .stream()
                        .map(PropertyMapper::toPropertyDto)
                        .collect(Collectors.toList())))
                .collectList()
                .map(data -> {
                    log.debug("Returning {} properties for user with id {} on page {} (page size={})",
                            data.size(), userId, currentPage, PAGE_SIZE);

                    result.setProperties(data);
                    return result;
                }));
    }

    @Override
    public Mono<PropertyAggregate> getProperty(Long propertyId) {
        log.info("Getting property by id: {}", propertyId);
        PropertyAggregate propertyAggregate = new PropertyAggregate();
        return reactiveUtils.asyncMono(() -> Mono.just(propertyRepository.findById(propertyId)
                        .orElseThrow(() ->
                                new NotFoundException(String.format("Property with id %d not found ", propertyId))))
                .map(PropertyMapper::toPropertyDetailsDto)
                .map(propertyDetailsDto ->
                        propertyServiceHelper.setPropertyAggregateFromDto(propertyAggregate, propertyDetailsDto))
                .flatMap(property -> userServiceIntegration.getUserById(property.getOwnerId()))
                .map(userDto -> {
                    propertyServiceHelper.setOwnerDetails(propertyAggregate, userDto);
                    log.debug("Property retrieved successfully: {}", propertyAggregate);
                    return propertyAggregate;
                })
                .doOnError(throwable ->
                        log.error("Failed to get property by id: {}, error: {}", propertyId, throwable.getMessage()))
        );
    }

    @Override
    public Mono<Void> createProperty(Flux<FilePart> filePartFlux, String propertyJson) {
        PropertyDetailsDto propertyDetailsDto = propertyServiceHelper.deserializePropertyDetails(propertyJson);

        log.info("Creating new property for owner: {}", propertyDetailsDto.getOwnerId());

        propertyServiceHelper.processAndUploadImages(filePartFlux, propertyDetailsDto);
        Property property = propertyServiceHelper.mapPropertyDetailsToProperty(propertyDetailsDto);

        return propertyServiceHelper.saveProperty(property)
                .doOnSuccess(p -> log.info("New property created successfully. Property ID: {}", p.getId()))
                .then();
    }

    @Override
    public Mono<Void> deleteProperty(Long id) {
        log.info("Deleting property with id: {}", id);

        propertyRepository.deleteById(id);
        log.info("Property with id {} deleted successfully", id);

        reservationServiceIntegration.deleteAllReservationsByPropertyId(id);
        log.info("All reservations for property with id {} deleted successfully", id);

        return Mono.empty();
    }


    @Override
    public Mono<PropertyReservationDataDto> getPropertyById(Long propertyId) {
        log.info("Retrieving property with ID: {}", propertyId);

        return reactiveUtils.asyncMono(() -> Mono.just(propertyRepository.getPropertyById(propertyId))
                        .map(PropertyMapper::toPropertyReservationDataDto))
                        .doOnSuccess(property ->
                                log.info("Property with ID {} successfully retrieved: {}", propertyId, property))
                        .doOnError(error ->
                                log.error("Failed to retrieve property with ID {}: {}", propertyId, error.getMessage()));
    }
}
