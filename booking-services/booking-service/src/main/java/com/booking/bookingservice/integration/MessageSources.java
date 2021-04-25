package com.booking.bookingservice.integration;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MessageSources {
    String OUTPUT_PROPERTIES = "output-properties";
    String OUTPUT_USERS = "output-users";

    @Output(OUTPUT_PROPERTIES)
    MessageChannel outputProperties();

    @Output(OUTPUT_USERS)
    MessageChannel outputUsers();
}
