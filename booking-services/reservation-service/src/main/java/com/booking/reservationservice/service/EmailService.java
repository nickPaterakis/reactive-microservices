package com.booking.reservationservice.service;

import com.booking.bookingapi.reservation.dto.ReservationDetailsDto;
import com.booking.bookingapi.user.dto.UserDto;

import javax.mail.MessagingException;

public interface EmailService {

    void sendReservationDetailsToOwner(ReservationDetailsDto reservationDetailsDto, UserDto customer) throws MessagingException;

    void sendCancellationMessage(ReservationDetailsDto reservationDetailsDto, UserDto customer) throws MessagingException;
}
