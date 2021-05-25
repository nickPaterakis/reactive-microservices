package com.booking.bookingapi.core.user;

import com.booking.bookingapi.composite.dto.BookingUser;
import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@RequestMapping("/users")
public interface UserEndpoint extends UserService {

    @GetMapping("/me")
    @Override
    Mono<UserDetailsDto> getUserDetails(@AuthenticationPrincipal BookingUser user);

    @GetMapping("/{email}")
    @Override
    Mono<UserDetailsDto> findUserByEmail(@PathVariable String email);
}
