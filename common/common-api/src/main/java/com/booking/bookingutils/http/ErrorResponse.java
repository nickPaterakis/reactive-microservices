package com.booking.bookingutils.http;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ErrorResponse {

    HttpStatus httpStatus;

    String message;

    String path;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    ZonedDateTime timestamp;

    public ErrorResponse(HttpStatus httpStatus, String message, String path) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.path = path;
        this.timestamp = ZonedDateTime.now();
    }
}
