package com.booking.userservice.security;

import com.booking.commondomain.dto.user.BookingUser;
import com.booking.userservice.service.UserService;
import com.booking.commondomain.dto.user.UserDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BookingReactiveUserDetailsService implements ReactiveUserDetailsService {

    private final UserService userService;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userService.findUserByEmail(username)
                .switchIfEmpty(Mono.defer(Mono::empty))
                .map(BookingUser::new);
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
