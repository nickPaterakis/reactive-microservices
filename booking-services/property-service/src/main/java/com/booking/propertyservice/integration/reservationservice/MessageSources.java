package com.booking.propertyservice.integration.reservationservice;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MessageSources {
    String OUTPUT_RESERVATIONS = "output-reservations";

    @Output(OUTPUT_RESERVATIONS)
    MessageChannel outputReservations();
}
