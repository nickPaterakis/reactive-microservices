package com.booking.reservationservice.security;

import com.booking.bookingapi.user.dto.BookingUser;
import com.booking.bookingapi.reservation.ReservationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Log4j2
public class BookingReactiveUserDetailsService implements ReactiveUserDetailsService {

    private final ReservationService reservationService;

    public BookingReactiveUserDetailsService(@Qualifier("ReservationServiceImpl") ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        log.info("Finding user for user name {}", username);
        return reservationService.findUserByEmail(username).switchIfEmpty(Mono.empty()).map(BookingUser::new);
    }
}
