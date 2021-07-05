package com.booking.reservationservice.service;

import com.booking.bookingapi.reservation.ReservationService;
import com.booking.bookingapi.reservation.dto.ReservationDetailsDto;
import com.booking.bookingapi.reservation.dto.ReservationDto;
import com.booking.bookingapi.user.dto.BookingUser;
import com.booking.bookingapi.user.dto.UserDetailsDto;
import com.booking.reservationservice.integration.ReservationIntegration;
import com.booking.reservationservice.mapper.ReservationMapper;
import com.booking.reservationservice.model.Reservation;
import com.booking.reservationservice.repository.ReservationRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

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
        return  reservationRepository.findPropertyIds(location, checkIn, checkOut);
    }

    @Override
    public Flux<ReservationDetailsDto> getReservationsByUserId(@AuthenticationPrincipal BookingUser user) {
        log.info("Get Reservations By User Id: {}", user.getId());

        return reservationRepository
                .findByUserId(UUID.fromString(user.getId()))
                .map(reservation ->
                    Mono.zip(
                            integration.getPropertyById(reservation.getPropertyId()),
                            integration.getUserById(reservation.getOwnerId()),
                            (propertyReservationDataDto, userDto) -> new ReservationDetailsDto()
                                    .setId(reservation.getId().toString())
                                    .setCheckIn(reservation.getCheckIn())
                                    .setCheckOut(reservation.getCheckOut())
                                    .setPropertyReservationDataDto(propertyReservationDataDto)
                                    .setUserDto(userDto)
                                    .setPropertyId(reservation.getPropertyId())
                                    .setPrice(reservation.getPrice())
                    )
                    .block()
                );
    }

    @Override
    public Mono<Void> createReservation(ReservationDto reservationDto) {
        log.info("createReservation");
        Reservation reservation = ReservationMapper.toReservation(reservationDto);
        reservationRepository.save(reservation).subscribe();
        return Mono.empty();
    }

    @Override
    public Mono<Void> deleteReservation(UUID reservationId) {
        log.info("deleteReservation: {}", reservationId.toString());
        reservationRepository.deleteById(reservationId).subscribe();
        return Mono.empty();
    }

    @Override
    public Mono<Void> deleteAllReservationsByPropertyId(Long propertyId) {
        log.info("Delete All Reservations By property Id: {}", propertyId);
        reservationRepository.deleteAllByPropertyId(propertyId).subscribe();
        return Mono.empty();
    }

    @Override
    public Mono<UserDetailsDto> findUserByEmail(String email) {
        log.info("findUserByEmail: {}", email);
        return integration.findUserByEmail(email);
    }
}
