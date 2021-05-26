package com.booking.reservationservice.service;

import com.booking.bookingapi.core.reservation.ReservationService;
import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import com.booking.reservationservice.integration.ReservationIntegration;
import com.booking.reservationservice.model.Reservation;
import com.booking.reservationservice.repository.ReservationRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service("ReservationServiceImpl")
@Log4j2
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationIntegration integration;

    @Autowired
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationIntegration integration) {
        this.reservationRepository = reservationRepository;
        this.integration = integration;
    }

    @Override
    public Flux<Long> getPropertyIds(String location, LocalDate checkIn, LocalDate checkOut) {
        log.info("Get property ids");
        reservationRepository.findAll().subscribe(System.out::println);
        Query query = new Query();

        query.addCriteria(Criteria.where("location").is(location));
        return reactiveMongoTemplate.find(query, Reservation.class)
                .collectList().map( data ->
                        Flux.just(data.get(1))
        );
        // return mongo
        //return reservationRepository.findPropertyIds(location, checkIn, checkOut);
    }

    @Override
    public Mono<UserDetailsDto> findUserByEmail(String email) {
        log.info("findUserByEmail: {}", email);
        return integration.findUserByEmail(email);
    }
}
