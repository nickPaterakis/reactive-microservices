package com.booking.propertyservice.service;

import com.booking.bookingapi.core.property.Dto.PageProperties;
import com.booking.bookingapi.core.property.Dto.PropertyDetailsDto;
import com.booking.bookingapi.core.property.PropertyService;
import com.booking.bookingutils.exception.NotFoundException;
import com.booking.propertyservice.dto.mapper.PropertyMapper;
import com.booking.propertyservice.model.Property;
import com.booking.propertyservice.repository.PropertyRepository;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.time.LocalDate;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service("PropertyServiceImpl")
@Log4j2
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final Scheduler scheduler;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository, Scheduler scheduler) {
        this.propertyRepository = propertyRepository;
        this.scheduler = scheduler;
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PageProperties> searchProperties(
            String location,
            LocalDate checkIn,
            LocalDate checkOut,
            int guestNumber,
            int currentPage
    ) {
        log.info("search properties by location: {}", location);
        Pageable pageable;
        pageable = PageRequest.of(currentPage,5);
        PageProperties result = new PageProperties();
        return asyncMono(() -> Mono.just(propertyRepository.count(location, checkIn, checkOut, guestNumber))
                .map(totalElements -> {
                    result.setTotalElements(totalElements);
                    return totalElements;
                })
                .flatMapMany(el -> Flux.fromIterable(propertyRepository.searchProperties(location, checkIn, checkOut, guestNumber, pageable)
                        .stream()
                        .map(PropertyMapper::toPropertyDto)
                        .collect(Collectors.toList())))
                .collectList()
                .map(data -> {
                    result.setProperties(data);
                    return result;
                }));
    }

    @Override
    public Mono<PageProperties> getProperties(UUID ownerId) {
        log.info("Get properties by user id: {}", ownerId.toString());
        Pageable pageable;
        pageable = PageRequest.of(0,5);
        PageProperties result = new PageProperties();
        return asyncMono(() -> Mono.just(propertyRepository.count(ownerId.toString()))
                .map(totalElements -> {
                    result.setTotalElements(totalElements);
                    return totalElements;
                })
                .flatMapMany(el -> Flux.fromIterable(propertyRepository.findByOwner(ownerId.toString(), pageable)
                        .stream()
                        .map(PropertyMapper::toPropertyDto)
                        .collect(Collectors.toList())))
                .collectList()
                .map(data -> {
                    result.setProperties(data);
                    return result;
                }));
    }

    @Override
    public Mono<PropertyDetailsDto> getProperty(Long propertyId) {
        return asyncMono(() -> Mono.just(propertyRepository.findById(propertyId)
                .map(PropertyMapper::toPropertyDetailsDto)
                .orElseThrow(() -> new NotFoundException(String.format("Property with id %d not found ", propertyId))))
        );
    }

    @Override
    public Mono<Void> createProperty(PropertyDetailsDto propertyDetailsDto) {
        Property property = PropertyMapper.toProperty(propertyDetailsDto);
        System.out.println(property);
        propertyRepository.save(property);
        return Mono.empty();
    }

    @Override
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }

    private <T> Flux<T> asyncFlux(Supplier<Publisher<T>> publisherSupplier) {
        return Flux.defer(publisherSupplier).subscribeOn(scheduler);
    }

    private <T> Mono<T> asyncMono(Supplier<Mono<T>> publisherSupplier) {
        return Mono.defer(publisherSupplier).subscribeOn(scheduler);
    }
}
