package com.booking.bookingapi.composite;

import com.booking.bookingapi.composite.request.UserDetailsRequest;
import com.booking.bookingapi.core.property.Dto.CountryDto;
import com.booking.bookingapi.core.property.Dto.PropertyDto;
import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import com.booking.bookingapi.core.user.security.BookingUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.time.LocalDate;

public interface BookingService {

    Flux<CountryDto> getCountries(String name);

    Flux<PropertyDto> searchProperties(
            String location,
            LocalDate checkIn,
            LocalDate checkOut,
            int guestNumber,
            int currentPage
    );

    Flux<PropertyDto> getProperties(String ownerId);

    Mono<UserDetailsDto> findUserByEmail(String email);

    Mono<UserDetailsDto> getUserDetails(@AuthenticationPrincipal BookingUser bookingUser);

    Mono<UserDetailsDto> saveUserDetails(UserDetailsRequest userDetailsRequest, Mono<Principal> principal);

}
