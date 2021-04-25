package com.booking.bookingapi.composite;

import com.booking.bookingapi.composite.request.UserDetailsRequest;
import com.booking.bookingapi.core.property.Dto.CountryDto;
import com.booking.bookingapi.core.property.Dto.PropertyDto;
import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import com.booking.bookingapi.core.user.security.BookingUser;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.time.LocalDate;

@RequestMapping("/booking/api/v1")
public interface BookingEndpoint extends BookingService  {

    @GetMapping("/countries/{name}")
    @Override
    Flux<CountryDto> getCountries(@PathVariable String name);

    @GetMapping("/properties/search")
    @Override
    Flux<PropertyDto> searchProperties(
            @RequestParam(value = "location") String location,
            @RequestParam(value = "checkIn") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
            @RequestParam(value = "checkOut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
            @RequestParam(value = "guestNumber") int guestNumber,
            @RequestParam(value = "currentPage") int currentPage
    );

    @GetMapping("properties/{ownerId}")
    @Override
    Flux<PropertyDto> getProperties(@PathVariable String ownerId);

    @GetMapping("users/me")
    @Override
    Mono<UserDetailsDto> getUserDetails(@AuthenticationPrincipal BookingUser user);

    @PostMapping("users/me")
    @Override
    Mono<UserDetailsDto> saveUserDetails(
            @RequestBody UserDetailsRequest updateUserDetailsRequest,
            Mono<Principal> principal);
}
