package com.booking.userservice.web.api;

import com.booking.bookingapi.core.user.UserEndpoint;
import com.booking.bookingapi.core.user.UserService;
import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@Log4j2
public class UserResource implements UserEndpoint {

    private final UserService userService;

    @Autowired
    public UserResource(@Qualifier("UserServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @Override
    public Mono<UserDetailsDto> getUserDetails(UUID uuid) {
        return userService.getUserDetails(uuid);
    }

    @Override
    public Mono<UserDetailsDto> findUserByEmail(String email) {
        return userService.findUserByEmail(email);
    }

    @Override
    public UserDetailsDto saveUserDetails(UserDetailsDto userDetailsDto) {
        return userService.saveUserDetails(userDetailsDto);
    }
}

