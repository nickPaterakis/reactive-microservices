package com.booking.bookingservice.service;

import com.booking.bookingapi.composite.BookingService;
import com.booking.bookingapi.composite.dto.PropertyAggregate;
import com.booking.bookingapi.composite.request.UserDetailsRequest;
import com.booking.bookingapi.core.property.Dto.CountryDto;
import com.booking.bookingapi.core.property.Dto.PageProperties;
import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import com.booking.bookingservice.integration.BookingIntegration;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
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
    public Mono<PageProperties> getProperties(@AuthenticationPrincipal Jwt jwt) {
        return integration.getProperties(UUID.fromString("76393fab-10b2-40bb-b3ef-b75a76829178"));
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
                            .setCountry(propertyDetailsDto.getCountry())
                            .setImage(propertyDetailsDto.getImage())
                            .setAmenities(propertyDetailsDto.getAmenities())
                            .setUserId(propertyDetailsDto.getUserId());
                    return propertyAggregate;
                })
                .flatMap(pa -> integration.getUserDetails(pa.getUserId()))
                .map(userDetailsDto -> {
                    propertyAggregate
                            .setUserName(userDetailsDto.getFirstName() + " " + userDetailsDto.getLastName());
                    return propertyAggregate;
                });
    }

    @Override
    public Mono<UserDetailsDto> getUserDetails(@AuthenticationPrincipal Jwt jwt) {
        System.out.println(jwt.getClaims().get("sub"));
        return integration.getUserDetails(UUID.fromString("76393fab-10b2-40bb-b3ef-b75a76829178"))
                .onErrorMap(Throwable::getCause);
    }

    @Override
    public Mono<UserDetailsDto> findUserByEmail(String email) {
        log.info("findUserByEmail: {}", email);
        return integration.findUserByEmail(email);
    }

    @Override
    public Mono<UserDetailsDto> saveUserDetails(UserDetailsRequest userDetailsRequest, Mono<Principal> principal) {
        log.info("saveUserDetails: {}", userDetailsRequest.getEmail());
        return Mono.just(integration.saveUserDetails(createUser(userDetailsRequest)));
    }

    UserDetailsDto createUser(UserDetailsRequest userDetailsRequest) {
        return new UserDetailsDto()
                .setId(UUID.fromString("76393fab-10b2-40bb-b3ef-b75a76829178"))
                .setFirstName(userDetailsRequest.getFirstName())
                .setLastName(userDetailsRequest.getLastName())
                .setEmail(userDetailsRequest.getEmail());
    }



}
