package com.booking.userservice.dto.mapper;

import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import com.booking.userservice.model.User;

public class UserMapper {

    public static User toUser(UserDetailsDto userDetailsDto) {
        return new User()
                .setId(userDetailsDto.getId())
                .setFirstName(userDetailsDto.getFirstName())
                .setLastName(userDetailsDto.getLastName())
                .setEmail(userDetailsDto.getEmail())
                .setRules(userDetailsDto.getRoles());
    }
}
