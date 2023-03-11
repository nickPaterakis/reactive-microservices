package com.booking.reservationservice.integration.propertyservice;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MessageSources {
    String OUTPUT_USERS = "output-users";
    String OUTPUT_PROPERTIES = "output-properties";

    @Output(OUTPUT_USERS)
    MessageChannel outputUsers();

    @Output(OUTPUT_PROPERTIES)
    MessageChannel outputProperties();
}
