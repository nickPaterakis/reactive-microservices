package com.booking.propertyservice.integration.reservationservice;

import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface ReservationServiceIntegration {

    Flux<Long> getPropertyIds(String location, LocalDate checkIn, LocalDate checkOut);

    void deleteAllReservationsByPropertyId(Long propertyId);
}
