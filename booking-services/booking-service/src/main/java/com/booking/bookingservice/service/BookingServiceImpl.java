package com.booking.bookingservice.service;

import com.booking.bookingapi.composite.BookingService;
import com.booking.bookingapi.composite.request.UserDetailsRequest;
import com.booking.bookingapi.core.property.Dto.CountryDto;
import com.booking.bookingapi.core.property.Dto.PropertyDto;
import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import com.booking.bookingapi.core.user.security.BookingUser;
import com.booking.bookingservice.integration.BookingIntegration;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public Flux<PropertyDto> searchProperties(String location, LocalDate checkIn, LocalDate checkOut, int guestNumber, int currentPage) {
        return integration.searchProperties(location, checkIn, checkOut, guestNumber, currentPage);
    }

    @Override
    public Flux<PropertyDto> getProperties(String ownerId) {
        return null;
    }

    @Override
    public Mono<UserDetailsDto> getUserDetails(@AuthenticationPrincipal BookingUser user) {
        System.out.println(user.getId().toString());
        return integration.getUserDetails(UUID.fromString("76393fab-10b2-40bb-b3ef-b75a76829178"))
                .onErrorMap(Throwable::getCause);
    }

    @Override
    public Mono<UserDetailsDto> findUserByEmail(String email) {
        return null;
    }

    @Override
    public Mono<UserDetailsDto> saveUserDetails(UserDetailsRequest userDetailsRequest, Mono<Principal> principal) {
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
