package com.booking.bookingapi.core.reservation;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("reservations")
public interface ReservationEndpoint extends ReservationService {

    @GetMapping("/propertyIds")
    @Override
    Flux<Long> getPropertyIds(
            @RequestParam(value = "location") String location,
            @RequestParam(value = "checkIn") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
            @RequestParam(value = "checkOut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut
    );
}
