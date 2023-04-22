package com.booking.reservationservice.service.reservationservice;

import com.booking.commondomain.dto.reservation.ReservationDetailsDto;
import com.booking.commondomain.dto.reservation.ReservationDto;
import com.booking.commondomain.dto.user.BookingUser;
import com.booking.reservationservice.integration.propertyservice.PropertyServiceIntegration;
import com.booking.reservationservice.integration.userservice.UserServiceIntegration;
import com.booking.reservationservice.mapper.ReservationMapper;
import com.booking.reservationservice.model.Reservation;
import com.booking.reservationservice.repository.ReservationRepository;
import com.booking.reservationservice.service.reservationservice.helper.ReservationServiceHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationServiceHelper reservationServiceHelper;
    private final UserServiceIntegration userServiceIntegration;
    private final PropertyServiceIntegration propertyServiceIntegration;

    @Override
    public Flux<Long> getPropertyIds(String location, LocalDate checkIn, LocalDate checkOut) {
        log.info("Get reserved property ids");
        return reservationRepository.findPropertyIds(location, checkIn, checkOut);
    }

    @Override
    public Flux<ReservationDetailsDto> getReservationsByUserId(@AuthenticationPrincipal BookingUser user) {
        log.info("Get Reservations By User Id: {}", user.getId());

        return reservationRepository
                .findByUserId(UUID.fromString(user.getId()))
                .flatMap(reservation ->
                        Mono.zip(propertyServiceIntegration.getPropertyById(reservation.getPropertyId()),
                                userServiceIntegration.getUserById(reservation.getOwnerId()),
                                (propertyReservationDataDto, userDto) -> reservationServiceHelper
                                        .createReservationDetailsDto(reservation, propertyReservationDataDto, userDto)));
    }

    @Override
    public Mono<ReservationDetailsDto> createReservation(ReservationDto reservationDto) {
        log.info("Create Reservation");

        Reservation reservation = ReservationMapper.toReservation(reservationDto);

        return reservationRepository.save(reservation)
                .flatMap(savedReservation ->
                        Mono.zip(propertyServiceIntegration.getPropertyById(reservation.getPropertyId()),
                                userServiceIntegration.getUserById(reservation.getOwnerId()),
                                (propertyReservationDataDto, userDto) -> reservationServiceHelper
                                        .createReservationDetailsDto(reservation, propertyReservationDataDto, userDto)))
                .map(reservationServiceHelper::sendCancellationMessage);
    }

    @Override
    public Mono<Void> deleteReservation(String reservationId) {
        log.info("Delete Reservation with reservation id: {}", reservationId);

        return reservationRepository.findById(reservationId)
                .flatMap(reservation ->
                        Mono.zip(propertyServiceIntegration.getPropertyById(reservation.getPropertyId()),
                                userServiceIntegration.getUserById(reservation.getOwnerId()),
                                (propertyReservationDataDto, userDto) -> reservationServiceHelper
                                        .createReservationDetailsDto(reservation, propertyReservationDataDto, userDto)))
                .map(reservationServiceHelper::sendCancellationMessage)
                .then(reservationRepository.deleteById(reservationId));
    }

    @Override
    public void deleteAllReservationsByPropertyId(Long propertyId) {
        log.info("Delete All Reservations By property Id: {}", propertyId);

        reservationRepository.deleteAllByPropertyId(propertyId).subscribe();
    }
}
