package com.booking.reservationservice.infra;

import com.booking.bookingapi.event.Event;
import com.booking.bookingapi.reservation.ReservationService;
import com.booking.bookingapi.reservation.dto.ReservationDto;
import com.booking.bookingutils.exception.EventProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

import static java.lang.String.valueOf;

@EnableBinding(Sink.class)
@Log4j2
public class MessageProcessor {

    private final ReservationService reservationService;
    private final Validator validator;

    @Autowired
    public MessageProcessor(@Qualifier("ReservationServiceImpl") ReservationService reservationService,
                            Validator validator) {
        this.reservationService = reservationService;
        this.validator = validator;
    }

    @StreamListener(target = Sink.INPUT, condition = "headers['type']==1")
    public void createReservation(Event<Long, ReservationDto> event) {
        log.info("Process message created at {}...", event.getKey());

        if (event.getEventType() == Event.Type.CREATE) {
            ReservationDto reservationDto = event.getData();
            Set<ConstraintViolation<ReservationDto>> violations = validator.validate(reservationDto);
            System.out.println(reservationDto);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }

            reservationService.createReservation(reservationDto);
        } else {
            String errorMessage =
                    "Incorrect event type: "
                            .concat(valueOf(event.getEventType()))
                            .concat(", expected a CREATE or DELETE event");
            log.warn(errorMessage);
            throw new EventProcessingException(errorMessage);
        }
    }

    @StreamListener(target = Sink.INPUT, condition = "headers['type']==2")
    public void deleteAllReservationsByPropertyId(Event<Long, Long> event) {
        log.info("Process message created at {}...", event.getKey());

        if (event.getEventType() == Event.Type.DELETE) {
            Long propertyId = event.getData();
            reservationService.deleteAllReservationsByPropertyId(propertyId);
        } else {
            String errorMessage =
                    "Incorrect event type: "
                            .concat(valueOf(event.getEventType()))
                            .concat(", expected a CREATE or DELETE event");
            log.warn(errorMessage);
            throw new EventProcessingException(errorMessage);
        }
    }
}
