package com.booking.commondomain.dto.property;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyAggregate {

    @NotNull
    @Positive
    private Long id;

    @NotEmpty
    @Length(min=5, max=100)
    private String title;

    @NotEmpty
    private String propertyType;

    @NotEmpty
    private String guestSpace;

    @NotNull
    @Max(value = 16)
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
    @Length(min=10, max=250)
    private String description;

    @Valid
    private AddressDto address;

    @NotEmpty
    private Set<String> images;

    @Valid
    private Set<AmenityDto> amenities;

    @NotEmpty
    private String ownerFirstName;

    @NotEmpty
    private String ownerLastName;

    private UUID ownerId;

    private String ownerImage;

}