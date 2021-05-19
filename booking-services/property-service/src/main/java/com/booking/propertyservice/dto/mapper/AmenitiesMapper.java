package com.booking.propertyservice.dto.mapper;

import com.booking.propertyservice.model.Amenity;

public class AmenitiesMapper {

    public static Amenity toAmenity(String amenity) {
        return new Amenity(amenity);
    }
}
