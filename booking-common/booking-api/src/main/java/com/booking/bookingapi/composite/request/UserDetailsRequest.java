package com.booking.bookingapi.composite.request;

import lombok.Data;

@Data
public class UserDetailsRequest {

    private String firstName;
    private String lastName;
    private String email;
}
