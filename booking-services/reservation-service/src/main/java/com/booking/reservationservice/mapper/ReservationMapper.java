package com.booking.reservationservice.mapper;

import com.booking.bookingapi.reservation.dto.ReservationDto;
import com.booking.reservationservice.model.Reservation;

import java.util.UUID;

public class ReservationMapper {

    public static Reservation toReservation(ReservationDto reservationDto) {
        return new Reservation()
                .setId( UUID.randomUUID())
                .setCheckIn(reservationDto.getCheckIn())
                .setCheckOut(reservationDto.getCheckOut())
                .setLocation(reservationDto.getLocation())
                .setPropertyId(reservationDto.getPropertyId())
                .setPrice(reservationDto.getPrice())
                .setUserId(reservationDto.getUserId())
                .setOwnerId(reservationDto.getOwnerId());
    }

    public static ReservationDto toReservationDto(Reservation reservation) {
        return new ReservationDto()
                .setCheckIn(reservation.getCheckIn())
                .setCheckOut(reservation.getCheckOut())
                .setLocation(reservation.getLocation())
                .setPropertyId(reservation.getPropertyId())
                .setPrice(reservation.getPrice())
                .setUserId(reservation.getUserId())
                .setOwnerId(reservation.getOwnerId());
    }
}
