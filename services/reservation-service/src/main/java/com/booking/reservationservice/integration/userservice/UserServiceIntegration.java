package com.booking.reservationservice.integration.userservice;

import com.booking.commondomain.dto.user.UserDetailsDto;
import com.booking.commondomain.dto.user.UserDto;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserServiceIntegration {

    Mono<UserDetailsDto> findUserByEmail(String email);

    Mono<UserDto> getUserById(UUID userId);
}
