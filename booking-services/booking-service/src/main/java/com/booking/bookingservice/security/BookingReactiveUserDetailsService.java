package com.booking.bookingservice.security;

import com.booking.bookingapi.composite.BookingService;
import com.booking.bookingapi.composite.dto.BookingUser;
import com.booking.bookingapi.composite.request.UserDetailsRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Log4j2
public class BookingReactiveUserDetailsService implements ReactiveUserDetailsService {

    private final BookingService bookingService;

    public BookingReactiveUserDetailsService(@Qualifier("BookingServiceImpl") BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        log.info("Finding user for user name {}", username);
        return bookingService.findUserByEmail(username).switchIfEmpty(Mono.empty()).map(BookingUser::new);
    }

    public Mono<UserDetails> saveUser(Jwt jwt) {
        UserDetailsRequest userDetailsRequest = new UserDetailsRequest(jwt.getClaimAsString("given_name"), jwt.getClaimAsString("family_name"), jwt.getClaimAsString("email"));
        return bookingService.saveUserDetails(userDetailsRequest, jwt).map(BookingUser::new);
    }
}
