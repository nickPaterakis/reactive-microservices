package com.booking.reservationservice.web.api;

import com.booking.bookingapi.core.reservation.ReservationEndpoint;
import com.booking.bookingapi.core.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@RestController
public class ReservationResource implements ReservationEndpoint {

    private final ReservationService reservationService;

    @Autowired
    public ReservationResource(@Qualifier("ReservationServiceImpl") ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public Flux<Long> getPropertyIds(String location, LocalDate checkIn, LocalDate checkOut) {
        return reservationService.getPropertyIds(location, checkIn, checkOut);
    }
}
