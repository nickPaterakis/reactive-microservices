package com.booking.userservice.repository;

import com.booking.bookingapi.user.dto.UserDetailsDto;

import java.util.UUID;

public interface UserCustomRepository {

    void updateProfileImage(UUID userId, String profileImagePath);

    void updateUser(UserDetailsDto userDetailsDto);
}
