package com.booking.reservationservice.repository;

import com.booking.reservationservice.model.Reservation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface ReservationRepository extends ReactiveMongoRepository<Reservation, UUID>,
ReservationCustomRepository {

    Flux<Reservation> findByUserId(UUID userId);

    Mono<Void> deleteAllByPropertyId(Long propertyId);
}
