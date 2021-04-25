package com.booking.bookingapi.core.property.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyDto {

    private Long id;

    private String title;

    private String propertyType;

    private String guestSpace;

    private Integer maxGuestNumber;

    private Integer bedroomNumber;

    private Integer bathNumber;

    private Float pricePerNight;

    private String country;

    private String image;

    private Set<String> amenities;

}
