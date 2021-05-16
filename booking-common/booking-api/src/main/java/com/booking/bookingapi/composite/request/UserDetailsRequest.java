package com.booking.bookingapi.composite.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDetailsRequest {

    private String firstName;
    private String lastName;
    private String email;
}
