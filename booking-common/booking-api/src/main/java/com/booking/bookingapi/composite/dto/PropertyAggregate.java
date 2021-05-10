package com.booking.bookingapi.composite.dto;

import com.booking.bookingapi.core.property.Dto.AddressDto;
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
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyAggregate {

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

    private String ownerFirstName;

    private String ownerLastName;

    private UUID ownerId;

}