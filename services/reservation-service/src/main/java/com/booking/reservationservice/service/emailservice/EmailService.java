package com.booking.reservationservice.service.emailservice;

import com.booking.commondomain.dto.reservation.ReservationDetailsDto;
import com.booking.commondomain.dto.user.UserDto;

import javax.mail.MessagingException;

public interface EmailService {

    void sendReservationDetailsToOwner(ReservationDetailsDto reservationDetailsDto, UserDto customer) throws MessagingException;

    void sendCancellationMessage(ReservationDetailsDto reservationDetailsDto, UserDto customer) throws MessagingException;
}
