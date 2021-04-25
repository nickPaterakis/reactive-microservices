package com.booking.bookingservice.web.api;

import com.booking.bookingapi.composite.BookingEndpoint;
import com.booking.bookingapi.composite.BookingService;
import com.booking.bookingapi.composite.request.UserDetailsRequest;
import com.booking.bookingapi.core.property.Dto.CountryDto;
import com.booking.bookingapi.core.property.Dto.PropertyDto;
import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import com.booking.bookingapi.core.user.security.BookingUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.time.LocalDate;

@RestController
@Log4j2
public class BookingResource implements BookingEndpoint {

    private final BookingService bookingService;


    @Autowired
    public BookingResource(@Qualifier("BookingServiceImpl") BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public Flux<CountryDto> getCountries(String name) {
        return bookingService.getCountries(name);
    }

    @Override
    public Flux<PropertyDto> searchProperties(String location, LocalDate checkIn, LocalDate checkOut, int guestNumber, int currentPage) {
        return bookingService.searchProperties(location, checkIn, checkOut, guestNumber, currentPage);
    }

    @Override
    public Flux<PropertyDto> getProperties(String ownerId) {
        return null;
    }

    @Override
    public Mono<UserDetailsDto> getUserDetails(@AuthenticationPrincipal BookingUser user) {
        return bookingService.getUserDetails(user);
    }

    @Override
    public Mono<UserDetailsDto> findUserByEmail(String email) {
        return null;
    }

    @Override
    public Mono<UserDetailsDto> saveUserDetails(UserDetailsRequest updateUserDetailsRequest, Mono<Principal> principal) {
        return bookingService.saveUserDetails(updateUserDetailsRequest, principal);
    }
}