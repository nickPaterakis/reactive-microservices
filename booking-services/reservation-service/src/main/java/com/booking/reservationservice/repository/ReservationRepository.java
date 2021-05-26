package com.booking.reservationservice.repository;

import com.booking.reservationservice.model.Reservation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservationRepository extends ReactiveMongoRepository<Reservation, UUID>,
ReservationCustomRepository {


}
