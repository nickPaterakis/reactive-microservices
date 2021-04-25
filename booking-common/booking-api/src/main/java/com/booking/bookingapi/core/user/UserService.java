package com.booking.bookingapi.core.user;

import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserService {
    Mono<UserDetailsDto> getUserDetails(UUID uuid);

    Mono<UserDetailsDto> findUserByEmail(String email);

    UserDetailsDto saveUserDetails(UserDetailsDto userDetailsDto);
}
