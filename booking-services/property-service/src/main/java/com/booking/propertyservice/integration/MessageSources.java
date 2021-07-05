package com.booking.propertyservice.integration;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MessageSources {
    String OUTPUT_USERS = "output-users";
    String OUTPUT_RESERVATIONS = "output-reservations";

    @Output(OUTPUT_USERS)
    MessageChannel outputUsers();

    @Output(OUTPUT_RESERVATIONS)
    MessageChannel outputReservations();
}
