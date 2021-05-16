package com.booking.userservice.dto.mapper;

import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import com.booking.userservice.model.User;

import java.util.UUID;

public class UserMapper {

    public static User toUser(UserDetailsDto userDetailsDto) {
        return new User()
                .setId(UUID.fromString(userDetailsDto.getId()))
                .setFirstName(userDetailsDto.getFirstName())
                .setLastName(userDetailsDto.getLastName())
                .setEmail(userDetailsDto.getEmail());
                //.setRoles(userDetailsDto.getRoles());
    }

    public static UserDetailsDto toUserDetailsDto(User user) {
        return new UserDetailsDto()
                .setId(user.getId().toString())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setEmail(user.getEmail())
                .setRoles(user.getRoles());
    }
}
