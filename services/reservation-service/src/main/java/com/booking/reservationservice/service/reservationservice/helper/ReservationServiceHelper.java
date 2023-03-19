package com.booking.reservationservice.service.reservationservice.helper;

import com.booking.commondomain.dto.property.PropertyReservationDataDto;
import com.booking.commondomain.dto.reservation.ReservationDetailsDto;
import com.booking.commondomain.dto.user.UserDto;
import com.booking.reservationservice.model.Reservation;
import com.booking.reservationservice.service.emailservice.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
@RequiredArgsConstructor
public class ReservationServiceHelper {

    private final EmailService emailService;

    public ReservationDetailsDto createReservationDetailsDto(Reservation reservation, PropertyReservationDataDto propertyReservationDataDto, UserDto userDto) {
        return new ReservationDetailsDto()
                .setId(reservation.getId())
                .setCheckIn(reservation.getCheckIn())
                .setCheckOut(reservation.getCheckOut())
                .setPropertyReservationDataDto(propertyReservationDataDto)
                .setUserDto(userDto)
                .setPropertyId(reservation.getPropertyId())
                .setPrice(reservation.getPrice());
    }

    public ReservationDetailsDto sendCancellationMessage(ReservationDetailsDto reservationDetailsDto) {
        try {
            emailService.sendCancellationMessage(reservationDetailsDto, reservationDetailsDto.getUserDto());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return reservationDetailsDto;
    }
}
