package com.booking.bookingapi.core.reservation;

import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface ReservationService {

    Flux<Long> getPropertyIds(String location, LocalDate checkIn, LocalDate checkOut);

    default Mono<UserDetailsDto> findUserByEmail(String email){return null;};

}
