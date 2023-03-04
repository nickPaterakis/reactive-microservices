package com.booking.userservice.service;

import com.booking.commondomain.dto.user.BookingUser;
import com.booking.commondomain.dto.user.UserDetailsDto;
import com.booking.commondomain.dto.user.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserService {

    default Mono<UserDetailsDto> getUserDetails(@AuthenticationPrincipal BookingUser user){ return null; };

    Mono<UserDetailsDto> findUserByEmail(String email);

    default Mono<UserDetailsDto> saveUserDetails(UserDetailsDto userDetailsDto){ return null; };

    Mono<UserDto> getUserById(UUID userId);

    default Mono<Void> updateUser(UserDetailsDto userDetailsDto){ return null; };

    default ResponseEntity uploadImage(String userId, String path){ return null; }

}
