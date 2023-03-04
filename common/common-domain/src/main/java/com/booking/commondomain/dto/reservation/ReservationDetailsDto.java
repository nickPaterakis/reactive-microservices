package com.booking.commondomain.dto.reservation;

import com.booking.commondomain.dto.property.PropertyReservationDataDto;
import com.booking.commondomain.dto.user.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationDetailsDto {

    @NotEmpty
    private String id;

    @NotNull
    private LocalDate checkIn;

    @NotNull
    private LocalDate checkOut;

    @NotNull
    private Long propertyId;

    @NotNull
    @Digits(integer=10, fraction=2)
    private BigDecimal price;

    @Valid
    private PropertyReservationDataDto propertyReservationDataDto;

    @Valid
    private UserDto userDto;
}
