package com.booking.bookingapi.property.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyReservationDataDto {

    @NotEmpty
    private String title;

    @NotEmpty
    private String propertyType;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    private float pricePerNight;

    @NotEmpty
    private String location;

}
