package com.booking.reservationservice.service;

import com.booking.bookingapi.property.Dto.PropertyReservationDataDto;
import com.booking.bookingapi.reservation.ReservationService;
import com.booking.bookingapi.reservation.dto.ReservationDetailsDto;
import com.booking.bookingapi.reservation.dto.ReservationDto;
import com.booking.bookingapi.user.dto.BookingUser;
import com.booking.bookingapi.user.dto.UserDetailsDto;
import com.booking.bookingapi.user.dto.UserDto;
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

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.UUID;

@Service("ReservationServiceImpl")
@Log4j2
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationIntegration integration;
    private final EmailService emailService;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  ReservationIntegration integration, EmailService emailService) {
        this.reservationRepository = reservationRepository;
        this.integration = integration;
        this.emailService = emailService;
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
                    ).blockOptional().get());
    }

    @Override
    public Mono<Void> createReservation(ReservationDto reservationDto) {
        log.info("createReservation");
        Reservation reservation = ReservationMapper.toReservation(reservationDto);
        reservationRepository.save(reservation).subscribe();

        Mono<ReservationDetailsDto> reservationDetailsDtoMono = Mono.zip(
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
        );

        Mono<UserDto> userDtoMono = integration.getUserById(reservation.getUserId());

        Mono.zip(
                (data) -> {
                    ReservationDetailsDto reservationDetailsDto = (ReservationDetailsDto) data[0];
                    UserDto customer = (UserDto) data[1];
                    try {
                        emailService.sendReservationDetailsToOwner(reservationDetailsDto, customer);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                    return Mono.empty();
                },
                reservationDetailsDtoMono,
                userDtoMono
                ).subscribe();

        return Mono.empty();
    }

    @Override
    public Mono<Void> deleteReservation(UUID reservationId) {
        log.info("deleteReservation: {}", reservationId.toString());

        Mono<Reservation> reservationMono = reservationRepository.findById(reservationId);

        Mono<ReservationDetailsDto> reservationDetailsDtoMono = reservationMono
                .map(reservation ->
                        Mono.zip((data) -> {
                                    PropertyReservationDataDto propertyReservationDataDto = (PropertyReservationDataDto) data[0];
                                    UserDto userDto = (UserDto) data[1];
                                     return new ReservationDetailsDto()
                                            .setId(reservation.getId().toString())
                                            .setCheckIn(reservation.getCheckIn())
                                            .setCheckOut(reservation.getCheckOut())
                                            .setPropertyReservationDataDto(propertyReservationDataDto)
                                            .setUserDto(userDto)
                                            .setPropertyId(reservation.getPropertyId())
                                            .setPrice(reservation.getPrice());
                                },
                                integration.getPropertyById(reservation.getPropertyId()),
                                integration.getUserById(reservation.getOwnerId())
                        ).blockOptional().get()
                );

        Mono<UserDto> userDtoMono = reservationMono
                .map(reservation -> integration.getUserById(reservation.getUserId()).block());

        Mono.zip((data) -> {
                    ReservationDetailsDto reservationDetailsDto = (ReservationDetailsDto) data[0];
                    UserDto userDto = (UserDto) data[1];
                    try {
                        emailService.sendCancellationMessage(reservationDetailsDto, userDto);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                    return Mono.empty();
                },
                reservationDetailsDtoMono,
                userDtoMono
        ).subscribe();

        reservationRepository.deleteById(reservationId).subscribe();

        return Mono.empty();
    }

    @Override
    public Mono<Void> deleteAllReservationsByPropertyId(Long propertyId) {
        log.info("Delete All Reservations By property Id: {}", propertyId);
//        reservationRepository.findAllByPropertyId(propertyId)
//                .map(reservation -> reservation.getCheckIn().isAfter(LocalDate.now()) ? reservation : Mono.empty())
        reservationRepository.deleteAllByPropertyId(propertyId).subscribe();
        return Mono.empty();
    }

    @Override
    public Mono<UserDetailsDto> findUserByEmail(String email) {
        log.info("findUserByEmail: {}", email);
        return integration.findUserByEmail(email);
    }
}
