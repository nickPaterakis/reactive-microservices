package com.booking.userservice.security;

import com.booking.bookingapi.composite.dto.BookingUser;
import com.booking.bookingapi.core.user.UserService;
import com.booking.bookingapi.core.user.dto.UserDetailsDto;
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

    private final UserService userService;

    public BookingReactiveUserDetailsService(@Qualifier("UserServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        log.info("Finding user for user name {}", username);
        return userService.findUserByEmail(username).switchIfEmpty(Mono.empty()).map(BookingUser::new);
    }

    public Mono<UserDetails> saveUser(Jwt jwt) {
        UserDetailsDto userDetailsDto = new UserDetailsDto()
                .setId(jwt.getClaimAsString("sub"))
                .setEmail(jwt.getClaimAsString("email"))
                .setFirstName(jwt.getClaimAsString("given_name"))
                .setLastName(jwt.getClaimAsString("family_name"));

        return userService.saveUserDetails(userDetailsDto).map(BookingUser::new);
    }
}
