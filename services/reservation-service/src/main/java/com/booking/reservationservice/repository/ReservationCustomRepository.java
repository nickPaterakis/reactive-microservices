package com.booking.reservationservice.repository;

import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface ReservationCustomRepository {

    Flux<Long> findPropertyIds(String location, LocalDate checkIn, LocalDate checkOut);
}
