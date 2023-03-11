package com.booking.propertyservice.mapper;

import com.booking.commondomain.dto.property.AmenityDto;
import com.booking.propertyservice.model.Amenity;

public class AmenitiesMapper {

    public static Amenity toAmenity(AmenityDto amenityDto) {
        return new Amenity(amenityDto.getId(), amenityDto.getName());
    }

    public static AmenityDto toAmenity(Amenity amenity) {
        return new AmenityDto(amenity.getId(), amenity.getName());
    }
}
