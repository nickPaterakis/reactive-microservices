package com.booking.userservice.service;

import com.booking.commondomain.dto.user.BookingUser;
import com.booking.commondomain.dto.user.UserDetailsDto;
import com.booking.commondomain.dto.user.UserDto;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserService {

    Mono<UserDetailsDto> getUserDetails(@AuthenticationPrincipal BookingUser user);

    Mono<UserDetailsDto> findUserByEmail(String email);

    Mono<UserDetailsDto> saveUserDetails(UserDetailsDto userDetailsDto);

    Mono<UserDto> getUserById(UUID userId);

    Mono<Void> updateUser(UserDetailsDto userDetailsDto);

    Mono<Void> uploadImage(String userId, Mono<FilePart> filePartMono);

}
