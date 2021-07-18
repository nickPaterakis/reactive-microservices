package com.booking.bookingapi.property.Dto;

import com.booking.bookingapi.validationgroup.CreateProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyDetailsDto {

    @Null(groups = CreateProperty.class)
    @NotNull
    private Long id;

    @NotEmpty
    @Length(min=5, max=100)
    private String title;

    @Valid
    private PropertyTypeDto propertyType;

    @Valid
    private GuestSpaceDto guestSpace;

    @NotNull
    private Integer maxGuestNumber;

    @NotNull
    private Integer bedroomNumber;

    @NotNull
    private Integer bathNumber;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Positive
    private Float pricePerNight;

    @NotEmpty
    private String city;

    @Valid
    private CountryDto country;

    @NotEmpty
    private String postCode;

    @NotEmpty
    private String streetName;

    @NotNull
    private int streetNumber;

    @Valid
    private Set<String> images = new HashSet<String>();

    @NotEmpty
    @Length(min=10, max=250)
    private String description;

    @Valid
    private Set<AmenityDto> amenities;

    @NotNull
    private UUID ownerId;
}