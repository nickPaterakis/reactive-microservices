package com.booking.reservationservice.service;

import com.booking.bookingapi.core.reservation.ReservationService;
import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import com.booking.reservationservice.integration.ReservationIntegration;
import com.booking.reservationservice.repository.ReservationRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationIntegration integration) {
        this.reservationRepository = reservationRepository;
        this.integration = integration;
    }

    @Override
    public Flux<Long> getPropertyIds(String location, LocalDate checkIn, LocalDate checkOut) {
        log.info("Get property ids");
        return reservationRepository.findPropertyIds(location, checkIn, checkOut);
    }

    @Override
    public Mono<UserDetailsDto> findUserByEmail(String email) {
        log.info("findUserByEmail: {}", email);
        return integration.findUserByEmail(email);
    }
}
