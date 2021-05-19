package com.booking.bookingservice.service;

import com.booking.bookingapi.composite.BookingService;
import com.booking.bookingapi.composite.dto.BookingUser;
import com.booking.bookingapi.composite.dto.PropertyAggregate;
import com.booking.bookingapi.composite.request.UserDetailsRequest;
import com.booking.bookingapi.core.property.Dto.AddressDto;
import com.booking.bookingapi.core.property.Dto.CountryDto;
import com.booking.bookingapi.core.property.Dto.PageProperties;
import com.booking.bookingapi.core.property.Dto.PropertyDetailsDto;
import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import com.booking.bookingservice.integration.BookingIntegration;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Service("BookingServiceImpl")
@Log4j2
public class BookingServiceImpl implements BookingService {

    private final BookingIntegration integration;

    @Autowired
    public BookingServiceImpl(BookingIntegration integration) {
        this.integration = integration;
    }

    @Override
    public Flux<CountryDto> getCountries(String name) {
        return integration.getCountries(name);
    }

    @Override
    public Mono<PageProperties> searchProperties(String location, LocalDate checkIn, LocalDate checkOut, int guestNumber, int currentPage) {
        return integration.searchProperties(location, checkIn, checkOut, guestNumber, currentPage);
    }

    @Override
    public Mono<PageProperties> getProperties(@AuthenticationPrincipal BookingUser user) {
        log.info("getPropertiesByUserId: {}", user.getId());
        return integration.getProperties(UUID.fromString(user.getId()));
    }

    @Override
    public Mono<PropertyAggregate> getProperty(Long propertyId) {
        PropertyAggregate propertyAggregate = new PropertyAggregate();
        return integration.getProperty(propertyId)
                .map(propertyDetailsDto -> {
                    propertyAggregate
                            .setId(propertyDetailsDto.getId())
                            .setTitle(propertyDetailsDto.getTitle())
                            .setPropertyType(propertyDetailsDto.getPropertyType())
                            .setGuestSpace(propertyDetailsDto.getGuestSpace())
                            .setMaxGuestNumber(propertyDetailsDto.getMaxGuestNumber())
                            .setBedroomNumber(propertyDetailsDto.getBedroomNumber())
                            .setBathNumber(propertyDetailsDto.getBathNumber())
                            .setPricePerNight(propertyDetailsDto.getPricePerNight())
                            .setAddress(new AddressDto(
                                    propertyDetailsDto.getCity(),
                                    propertyDetailsDto.getCountry(),
                                    propertyDetailsDto.getPostCode(),
                                    propertyDetailsDto.getStreetName(),
                                    propertyDetailsDto.getStreetNumber()
                            ))
                            .setDescription(propertyDetailsDto.getDescription())
                            .setImage(propertyDetailsDto.getImage())
                            .setAmenities(propertyDetailsDto.getAmenities())
                            .setOwnerId(propertyDetailsDto.getOwnerId());
                    System.out.println(propertyDetailsDto.getOwnerId());
                    return propertyAggregate;
                })
                .flatMap(pa -> integration.getUserDetails(pa.getOwnerId()))
                .map(userDetailsDto -> {
                    propertyAggregate
                            .setOwnerFirstName(userDetailsDto.getFirstName())
                            .setOwnerLastName(userDetailsDto.getLastName());
                    return propertyAggregate;
                });
    }

    @Override
    public Mono<Void> createProperty(PropertyDetailsDto propertyDetailsDto) {
        log.info("createProperty: {}", propertyDetailsDto.getTitle());
        return integration.createProperty(propertyDetailsDto);
    }

    @Override
    public void deleteProperty(Long id) {
        log.info("deleteProperty: {}", id);
        integration.deleteProperty(id);
    }

    @Override
    public Mono<UserDetailsDto> getUserDetails(@AuthenticationPrincipal BookingUser user) {
        log.info("getUserDetails: {}", user.getEmail());
        return integration.getUserDetails(UUID.fromString(user.getId()))
                .onErrorMap(Throwable::getCause);
    }

    @Override
    public Mono<UserDetailsDto> findUserByEmail(String email) {
        log.info("findUserByEmail: {}", email);
        return integration.findUserByEmail(email);
    }

    @Override
    public Mono<UserDetailsDto> saveUserDetails(UserDetailsRequest userDetailsRequest, @AuthenticationPrincipal Jwt jwt) {
        log.info("saveUserDetails: {}", userDetailsRequest.getEmail());
        return Mono.just(integration.saveUserDetails(createUser(userDetailsRequest, jwt.getClaimAsString("sub"))));
    }

    UserDetailsDto createUser(UserDetailsRequest userDetailsRequest, String userId) {
        return new UserDetailsDto()
                .setId(userId)
                .setFirstName(userDetailsRequest.getFirstName())
                .setLastName(userDetailsRequest.getLastName())
                .setEmail(userDetailsRequest.getEmail());
    }



}
