package com.booking.reservationservice.web.api;

import com.booking.bookingapi.reservation.ReservationService;
import com.booking.bookingapi.reservation.dto.ReservationDetailsDto;
import com.booking.bookingapi.reservation.dto.ReservationDto;
import com.booking.bookingapi.user.dto.BookingUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import java.util.UUID;

@CrossOrigin("*")
@RequestMapping("reservations")
@RestController
@Validated
public class ReservationResource {

    private final ReservationService reservationService;

    @Autowired
    public ReservationResource(@Qualifier("ReservationServiceImpl") ReservationService reservationService) {
        this.reservationService = reservationService;
    }

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
    public Mono<Void> createReservation(@Valid @RequestBody ReservationDto reservationDto) {
        reservationService.createReservation(reservationDto);
        return Mono.empty();
    }

    @PreAuthorize("hasRole('BOOKING_USER')")
    @DeleteMapping("/delete/{reservationId}")
    public Mono<Void> deleteReservation(@PathVariable String reservationId) {
        reservationService.deleteReservation(reservationId);
        return Mono.empty();
    }
}
