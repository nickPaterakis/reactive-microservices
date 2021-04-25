package com.booking.bookingservice.security;

import com.booking.bookingapi.core.user.security.BookingUser;
import com.booking.bookingservice.service.BookingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BookingReactiveUserDetailsService implements ReactiveUserDetailsService {

    private static final Logger LOGGER =
      LoggerFactory.getLogger(com.booking.bookingservice.security.BookingReactiveUserDetailsService.class);

    private final BookingServiceImpl bookingService;

    public BookingReactiveUserDetailsService(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
    LOGGER.info("Finding user for user name {}", username);

    return bookingService.findUserByEmail(username).switchIfEmpty(Mono.empty()).map(BookingUser::new);
    }
}
