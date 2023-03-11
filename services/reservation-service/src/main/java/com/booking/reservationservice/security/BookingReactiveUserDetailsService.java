package com.booking.reservationservice.security;

import com.booking.commondomain.dto.user.BookingUser;
import com.booking.reservationservice.integration.userservice.UserServiceIntegration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookingReactiveUserDetailsService implements ReactiveUserDetailsService {

    private final UserServiceIntegration userServiceIntegration;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userServiceIntegration.findUserByEmail(username).switchIfEmpty(Mono.empty()).map(BookingUser::new);
    }
}
