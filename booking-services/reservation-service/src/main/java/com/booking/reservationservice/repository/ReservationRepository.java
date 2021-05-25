package com.booking.reservationservice.repository;

import com.booking.reservationservice.model.Reservation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface ReservationRepository extends ReactiveMongoRepository<Reservation, UUID>,
ReservationCustomRepository {


}
