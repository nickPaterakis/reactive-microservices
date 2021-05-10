package com.booking.bookingapi.core.property.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyDetailsDto {

    private Long id;

    private String title;

    private String propertyType;

    private String guestSpace;

    private Integer maxGuestNumber;

    private Integer bedroomNumber;

    private Integer bathNumber;

    private Float pricePerNight;

    private AddressDto address;

    private String image;

    private Set<String> amenities;

    private UUID ownerId;

}