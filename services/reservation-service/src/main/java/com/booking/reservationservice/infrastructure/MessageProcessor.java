package com.booking.reservationservice.infrastructure;

import com.booking.commondomain.event.Event;
import com.booking.reservationservice.service.reservationservice.ReservationService;
import com.booking.bookingutils.exception.EventProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import static java.lang.String.valueOf;

@Slf4j
@Component
@RequiredArgsConstructor
@EnableBinding(Sink.class)
public class MessageProcessor {

    private final ReservationService reservationService;

    @StreamListener(target = Sink.INPUT)
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
