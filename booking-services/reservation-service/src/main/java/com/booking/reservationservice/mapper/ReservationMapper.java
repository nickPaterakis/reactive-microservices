package com.booking.reservationservice.mapper;

import com.booking.commondomain.dto.reservation.ReservationDto;
import com.booking.reservationservice.model.Reservation;

public class ReservationMapper {

    public static Reservation toReservation(ReservationDto reservationDto) {
        return new Reservation()
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
