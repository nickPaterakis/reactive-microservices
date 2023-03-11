package com.booking.reservationservice.service.reservationservice;

import com.booking.commondomain.dto.reservation.ReservationDetailsDto;
import com.booking.commondomain.dto.reservation.ReservationDto;
import com.booking.commondomain.dto.user.BookingUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface ReservationService {

    Flux<Long> getPropertyIds(String location, LocalDate checkIn, LocalDate checkOut);

    Flux<ReservationDetailsDto> getReservationsByUserId(@AuthenticationPrincipal BookingUser user);

    Mono<Void> createReservation(ReservationDto reservationDto);

    Mono<Void> deleteReservation(String reservationId);

    void deleteAllReservationsByPropertyId(Long propertyId);
}
