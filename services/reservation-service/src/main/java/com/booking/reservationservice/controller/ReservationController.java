package com.booking.reservationservice.controller;

import com.booking.reservationservice.service.reservationservice.ReservationService;
import com.booking.commondomain.dto.reservation.ReservationDetailsDto;
import com.booking.commondomain.dto.reservation.ReservationDto;
import com.booking.commondomain.dto.user.BookingUser;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@CrossOrigin("*")
@RequestMapping("reservations")
@RestController
@Validated
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/propertyIds")
    Flux<Long> getPropertyIds(
            @NotEmpty @RequestParam(value = "location") String location,
            @NotNull @RequestParam(value = "checkIn") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
            @NotNull @RequestParam(value = "checkOut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut) {
        return reservationService.getPropertyIds(location, checkIn, checkOut);
    }

    @PreAuthorize("hasRole('BOOKING_USER')")
    @GetMapping("/my-reservations")
    Flux<ReservationDetailsDto> getReservationsByUserId(@AuthenticationPrincipal BookingUser user) {
        return reservationService.getReservationsByUserId(user);
    }

    @PreAuthorize("hasRole('BOOKING_USER')")
    @PostMapping("/create")
    public Mono<ReservationDetailsDto> createReservation(@Valid @RequestBody ReservationDto reservationDto) {
        return reservationService.createReservation(reservationDto);

    }

    @PreAuthorize("hasRole('BOOKING_USER')")
    @DeleteMapping("/delete/{reservationId}")
    public Mono<Void> deleteReservation(@PathVariable String reservationId) {
        return reservationService.deleteReservation(reservationId);
    }
}
