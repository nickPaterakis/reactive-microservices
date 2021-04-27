package com.booking.bookingapi.core.user;

import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequestMapping("/users")
public interface UserEndpoint extends UserService {

    @GetMapping("/me/{uuid}")
    @Override
    Mono<UserDetailsDto> getUserDetails(@PathVariable UUID uuid);

    @GetMapping("/{email}")
    @Override
    Mono<UserDetailsDto> findUserByEmail(@PathVariable String email);
}
