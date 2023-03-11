package com.booking.reservationservice.service.reservationservice;

import com.booking.commondomain.dto.property.PropertyReservationDataDto;
import com.booking.commondomain.dto.reservation.ReservationDetailsDto;
import com.booking.commondomain.dto.reservation.ReservationDto;
import com.booking.commondomain.dto.user.BookingUser;
import com.booking.commondomain.dto.user.UserDto;
import com.booking.reservationservice.integration.propertyservice.PropertyServiceIntegration;
import com.booking.reservationservice.integration.userservice.UserServiceIntegration;
import com.booking.reservationservice.mapper.ReservationMapper;
import com.booking.reservationservice.model.Reservation;
import com.booking.reservationservice.repository.ReservationRepository;
import com.booking.reservationservice.service.emailservice.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserServiceIntegration userServiceIntegration;
    private final PropertyServiceIntegration propertyServiceIntegration;
    private final EmailService emailService;

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
                            propertyServiceIntegration.getPropertyById(reservation.getPropertyId()),
                            userServiceIntegration.getUserById(reservation.getOwnerId()),
                            (propertyReservationDataDto, userDto) -> new ReservationDetailsDto()
                                    .setId(reservation.getId())
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

        if (reservation.getUserId() != null) {
            Mono<ReservationDetailsDto> reservationDetailsDtoMono = Mono.zip(
                    propertyServiceIntegration.getPropertyById(reservation.getPropertyId()),
                    userServiceIntegration.getUserById(reservation.getOwnerId()),
                    (propertyReservationDataDto, userDto) -> new ReservationDetailsDto()
                            .setId(reservation.getId())
                            .setCheckIn(reservation.getCheckIn())
                            .setCheckOut(reservation.getCheckOut())
                            .setPropertyReservationDataDto(propertyReservationDataDto)
                            .setUserDto(userDto)
                            .setPropertyId(reservation.getPropertyId())
                            .setPrice(reservation.getPrice())
            );

            Mono<UserDto> userDtoMono = userServiceIntegration.getUserById(reservation.getUserId());

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
        }

        return Mono.empty();
    }

    @Override
    public Mono<Void> deleteReservation(String reservationId) {
        log.info("deleteReservation: {}", reservationId);

        Mono<Reservation> reservationMono = reservationRepository.findById(reservationId);

        Mono<ReservationDetailsDto> reservationDetailsDtoMono = reservationMono
                .map(reservation ->
                        Mono.zip((data) -> {
                                    PropertyReservationDataDto propertyReservationDataDto = (PropertyReservationDataDto) data[0];
                                    UserDto userDto = (UserDto) data[1];
                                     return new ReservationDetailsDto()
                                            .setId(reservation.getId())
                                            .setCheckIn(reservation.getCheckIn())
                                            .setCheckOut(reservation.getCheckOut())
                                            .setPropertyReservationDataDto(propertyReservationDataDto)
                                            .setUserDto(userDto)
                                            .setPropertyId(reservation.getPropertyId())
                                            .setPrice(reservation.getPrice());
                                },
                                propertyServiceIntegration.getPropertyById(reservation.getPropertyId()),
                                userServiceIntegration.getUserById(reservation.getOwnerId())
                        ).blockOptional().get()
                );

        Mono<UserDto> userDtoMono = reservationMono
                .map(reservation -> userServiceIntegration.getUserById(reservation.getUserId()).block());

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
    public void deleteAllReservationsByPropertyId(Long propertyId) {
        log.info("Delete All Reservations By property Id: {}", propertyId);
        reservationRepository.deleteAllByPropertyId(propertyId).subscribe();
    }
}
