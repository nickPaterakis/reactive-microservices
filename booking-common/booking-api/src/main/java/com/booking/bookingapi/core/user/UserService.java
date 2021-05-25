package com.booking.bookingapi.core.user;

import com.booking.bookingapi.composite.dto.BookingUser;
import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import reactor.core.publisher.Mono;

public interface UserService {

    default Mono<UserDetailsDto> getUserDetails(@AuthenticationPrincipal BookingUser user){ return null; };

    Mono<UserDetailsDto> findUserByEmail(String email);

    default Mono<UserDetailsDto> saveUserDetails(UserDetailsDto userDetailsDto){ return null; };

}
