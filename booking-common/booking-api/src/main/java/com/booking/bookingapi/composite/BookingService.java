package com.booking.bookingapi.composite;

import com.booking.bookingapi.composite.dto.BookingUser;
import com.booking.bookingapi.composite.dto.PropertyAggregate;
import com.booking.bookingapi.composite.request.UserDetailsRequest;
import com.booking.bookingapi.core.property.Dto.CountryDto;
import com.booking.bookingapi.core.property.Dto.PageProperties;
import com.booking.bookingapi.core.property.Dto.PropertyDetailsDto;
import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookingService {

    Flux<CountryDto> getCountries(String name);
//
//    Mono<PageProperties> searchProperties(
//            String location,
//            LocalDate checkIn,
//            LocalDate checkOut,
//            int guestNumber,
//            int currentPage
//    );

    Mono<PageProperties> getProperties(@AuthenticationPrincipal BookingUser user);

    Mono<PropertyAggregate> getProperty(Long propertyId);

    Mono<Void> createProperty(PropertyDetailsDto propertyDetailsDto);

    void deleteProperty(Long id);
    
    Mono<UserDetailsDto> findUserByEmail(String email);

    Mono<UserDetailsDto> getUserDetails(@AuthenticationPrincipal BookingUser user);

    Mono<UserDetailsDto> saveUserDetails(UserDetailsRequest userDetailsRequest, @AuthenticationPrincipal Jwt jwt);

}
