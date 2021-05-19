package com.booking.propertyservice.dto.mapper;

import com.booking.bookingapi.core.property.Dto.PropertyDetailsDto;
import com.booking.bookingapi.core.property.Dto.PropertyDto;
import com.booking.propertyservice.model.*;

import java.util.*;
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
                .setCountry(property.getAddress().getCountry().getName())
                .setImage(property.getImages().iterator().next().getName())
                .setAmenities(property.getAmenities().stream().map(NameEntity::getName).collect(Collectors.toSet()));
    }

    public static PropertyDetailsDto toPropertyDetailsDto(Property property) {
        System.out.println(property.getOwner());
        return new PropertyDetailsDto()
                .setId(property.getId())
                .setTitle(property.getTitle())
                .setPropertyType(property.getPropertyType().getName())
                .setGuestSpace(property.getGuestSpace().getName())
                .setMaxGuestNumber(property.getMaxGuestNumber())
                .setBedroomNumber(property.getBedroomNumber())
                .setPricePerNight(property.getPricePerNight())
                .setBathNumber(property.getBathNumber())
                .setCity(property.getAddress().getCity())
                .setCountry(property.getAddress().getCountry().getName())
                .setPostCode(property.getAddress().getPostcode())
                .setStreetName(property.getAddress().getStreetName())
                .setStreetNumber(property.getAddress().getStreetNumber())
                .setImage(property.getImages().iterator().next().getName())
                .setAmenities(property.getAmenities().stream().map(NameEntity::getName).collect(Collectors.toSet()))
                .setDescription(property.getDescription())
                .setOwnerId(UUID.fromString(property.getOwner()));
    }

    public static Property toProperty(PropertyDetailsDto propertyDetailsDto) {
        Set<Image> images = new HashSet<Image>();
        images.add(new Image(propertyDetailsDto.getImage()));
        return new Property()
                .setTitle(propertyDetailsDto.getTitle())
                .setPropertyType(new PropertyType(propertyDetailsDto.getPropertyType()))
                .setGuestSpace(new GuestSpace(propertyDetailsDto.getGuestSpace()))
                .setMaxGuestNumber(propertyDetailsDto.getMaxGuestNumber())
                .setBedroomNumber(propertyDetailsDto.getBedroomNumber())
                .setPricePerNight(propertyDetailsDto.getPricePerNight())
                .setBathNumber(propertyDetailsDto.getBathNumber())
                .setAddress(new Address()
                        .setCity(propertyDetailsDto.getCity())
                        .setCountry(new Country(propertyDetailsDto.getCountry()))
                        .setPostcode(propertyDetailsDto.getPostCode())
                        .setStreetName(propertyDetailsDto.getStreetName())
                        .setStreetNumber(propertyDetailsDto.getStreetNumber())
                )
                .setAmenities(propertyDetailsDto.getAmenities().stream().map(AmenitiesMapper::toAmenity).collect(Collectors.toSet()))
                .setDescription(propertyDetailsDto.getDescription())
                .setOwner(propertyDetailsDto.getOwnerId().toString())
                .setImages(images);
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
