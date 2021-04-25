package com.booking.propertyservice.dto.mapper;

import com.booking.bookingapi.core.property.Dto.PropertyDto;
import com.booking.propertyservice.model.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PropertyMapper {

    public static PropertyDto toPropertyDto(Property property) {
        return new PropertyDto()
                .setId(property.getId())
                .setTitle(property.getTitle())
                .setPropertyType(property.getPropertyType().getName())
                .setGuestSpace(property.getGuestSpace().getName())
                .setMaxGuestNumber(property.getMaxGuestNumber())
                .setBedroomNumber(property.getBedroomNumber())
                .setPricePerNight(property.getPricePerNight())
                .setBathNumber(property.getBathNumber())
                .setCountry(property.getCountry().getName())
                .setImage(property.getImages().iterator().next().getName())
                .setAmenities(property.getAmenities().stream().map(NameEntity::getName).collect(Collectors.toSet()));
    }
//
//    public static Property toProperty(PropertyDto propertyDto) {
//        PropertyType propertyType = new PropertyType();
//        propertyType.setName(propertyDto.getPropertyType());
//        GuestSpace guestSpace = new GuestSpace();
//        guestSpace.setId(propertyDto.getGuestSpace());
//        guestSpace.setName(propertyDto.getGuestSpace());
//        return new Property()
//                //.setId(property.getId())
//                .setTitle(propertyDto.getTitle())
//                .setPropertyType(propertyType)
//                .setGuestSpace(new GuestSpace())
//                .setMaxGuestNumber(propertyDto.getMaxGuestNumber())
//                .setBedroomNumber(propertyDto.getBedroomNumber())
//                .setPricePerNight(propertyDto.getPricePerNight())
//                .setBathNumber(propertyDto.getBathNumber())
//                .setCountry(new Country().setName(propertyDto.getCountry()))
//                .setImages(new Set<Image>())
//                .setAmenities(property.getAmenities());
//    }
}
