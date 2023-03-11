package com.booking.propertyservice.mapper;

import com.booking.commondomain.dto.property.*;
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
        return new PropertyDetailsDto()
                .setId(property.getId())
                .setTitle(property.getTitle())
                .setPropertyType(new PropertyTypeDto().setName(property.getPropertyType().getName()))
                .setGuestSpace(new GuestSpaceDto().setName(property.getPropertyType().getName()))
                .setMaxGuestNumber(property.getMaxGuestNumber())
                .setBedroomNumber(property.getBedroomNumber())
                .setPricePerNight(property.getPricePerNight())
                .setBathNumber(property.getBathNumber())
                .setCity(property.getAddress().getCity())
                .setCountry(new CountryDto().setName(property.getAddress().getCountry().getName()))
                .setPostCode(property.getAddress().getPostcode())
                .setStreetName(property.getAddress().getStreetName())
                .setStreetNumber(property.getAddress().getStreetNumber())
                .setImages(property.getImages().stream().map(NameEntity::getName).collect(Collectors.toSet()))
                .setAmenities(property.getAmenities().stream().map(AmenitiesMapper::toAmenity).collect(Collectors.toSet()))
                .setDescription(property.getDescription())
                .setOwnerId(UUID.fromString(property.getOwner()));
    }

    public static Property toProperty(PropertyDetailsDto propertyDetailsDto) {
        Property property = new Property()
                .setTitle(propertyDetailsDto.getTitle())
                .setPropertyType(new PropertyType(
                        propertyDetailsDto.getPropertyType().getId(),
                        propertyDetailsDto.getPropertyType().getName())
                )
                .setGuestSpace(new GuestSpace(
                        propertyDetailsDto.getGuestSpace().getId(),
                        propertyDetailsDto.getGuestSpace().getName()))
                .setMaxGuestNumber(propertyDetailsDto.getMaxGuestNumber())
                .setBedroomNumber(propertyDetailsDto.getBedroomNumber())
                .setPricePerNight(propertyDetailsDto.getPricePerNight())
                .setBathNumber(propertyDetailsDto.getBathNumber())
                .setAddress(new Address()
                        .setCity(propertyDetailsDto.getCity())
                        .setCountry(new Country(propertyDetailsDto.getCountry().getId(), propertyDetailsDto.getCountry().getName()))
                        .setPostcode(propertyDetailsDto.getPostCode())
                        .setStreetName(propertyDetailsDto.getStreetName())
                        .setStreetNumber(propertyDetailsDto.getStreetNumber())
                )
                .setDescription(propertyDetailsDto.getDescription())
                .setOwner(propertyDetailsDto.getOwnerId().toString());

        propertyDetailsDto.getAmenities().forEach(amenityDto ->
            property.addAmenity(AmenitiesMapper.toAmenity(amenityDto))
        );
        propertyDetailsDto.getImages().forEach(imageName -> property.addImage(new Image(imageName)));
        return property;
    }

    public static PropertyReservationDataDto toPropertyReservationDataDto(Property property) {
        return new PropertyReservationDataDto()
                .setTitle(property.getTitle())
                .setPropertyType(property.getPropertyType().getName())
                .setPricePerNight(property.getPricePerNight())
                .setLocation(property.getAddress().getCountry().getName());
    }
}
