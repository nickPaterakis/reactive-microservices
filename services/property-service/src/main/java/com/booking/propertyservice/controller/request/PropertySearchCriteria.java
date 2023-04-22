package com.booking.propertyservice.controller.request;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Setter
@Getter
public class PropertySearchCriteria {

    @NotEmpty
    private String location;
    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate checkIn;

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate checkOut;

    @NotNull
    @Range(min = 0, max = 16)
    private int guestNumber;

    @NotNull
    @Min(value = 0)
    private int currentPage;
}
