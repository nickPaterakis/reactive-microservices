package com.booking.propertyservice.security;

import com.booking.commondomain.dto.user.BookingUser;
import com.booking.propertyservice.integration.userservice.UserServiceIntegration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingReactiveUserDetailsService implements ReactiveUserDetailsService {

    private final UserServiceIntegration userServiceIntegration;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        log.info("Finding user for user name {}", username);
        return userServiceIntegration.findUserByEmail(username)
                .switchIfEmpty(Mono.empty())
                .map(BookingUser::new);
    }
}
