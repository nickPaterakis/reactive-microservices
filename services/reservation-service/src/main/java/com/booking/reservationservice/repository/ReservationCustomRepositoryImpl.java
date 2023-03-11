package com.booking.reservationservice.repository;

import com.booking.reservationservice.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationCustomRepositoryImpl implements ReservationCustomRepository {

    ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    public ReservationCustomRepositoryImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Flux<Long> findPropertyIds(String location, LocalDate checkIn, LocalDate checkOut) {
        final Query query = new Query();
        final List<Criteria> criterias = new ArrayList<>();

        criterias.add(new Criteria().andOperator(
                Criteria.where("checkIn").lte(checkIn),
                Criteria.where("checkOut").gte(checkIn)
        ));

        criterias.add(new Criteria().andOperator(
                Criteria.where("checkIn").gte(checkIn),
                Criteria.where("checkIn").lte(checkOut)
        ));

        criterias.add(new Criteria().andOperator(
                Criteria.where("checkIn").gte(checkIn),
                Criteria.where("checkOut").lte(checkOut)
        ));

        criterias.add(new Criteria().andOperator(
                Criteria.where("checkIn").lte(checkIn),
                Criteria.where("checkOut").gte(checkOut)
        ));

        query.addCriteria(new Criteria().andOperator(
                Criteria.where("location").is(location),
                new Criteria().orOperator(criterias.toArray(new Criteria[criterias.size()]))));

        return reactiveMongoTemplate.find(query, Reservation.class, "reservations").map(Reservation::getPropertyId);
    }
}
