package com.booking.userservice.repository;

import com.booking.commondomain.dto.user.UserDetailsDto;
import com.booking.userservice.model.User;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserCustomRepository {

    void updateProfileImage(UUID userId, String profileImagePath);

    void updateUser(UserDetailsDto userDetailsDto);

    Mono<User> findUserByEmail(String email);
}
