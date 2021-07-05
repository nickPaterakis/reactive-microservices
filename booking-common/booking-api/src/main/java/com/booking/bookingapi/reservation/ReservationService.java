package com.booking.bookingapi.reservation;

import com.booking.bookingapi.reservation.dto.ReservationDetailsDto;
import com.booking.bookingapi.reservation.dto.ReservationDto;
import com.booking.bookingapi.user.dto.BookingUser;
import com.booking.bookingapi.user.dto.UserDetailsDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

public interface ReservationService {

    Flux<Long> getPropertyIds(String location, LocalDate checkIn, LocalDate checkOut);

    default Mono<UserDetailsDto> findUserByEmail(String email){return null;};

    default Mono<Void> createReservation(ReservationDto reservationDto){return Mono.empty();}

    default Mono<Void> deleteReservation(UUID reservationId){return Mono.empty();}

    default Flux<ReservationDetailsDto> getReservationsByUserId(@AuthenticationPrincipal BookingUser user){return null;}

    Mono<Void> deleteAllReservationsByPropertyId(Long propertyId);

}
