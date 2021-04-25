//package com.booking.bookingapi.composite.dto;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import lombok.*;
//import lombok.experimental.Accessors;
//
//import java.util.Set;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Accessors(chain = true)
//@JsonInclude(value = JsonInclude.Include.NON_NULL)
//@JsonIgnoreProperties(ignoreUnknown = true)
//public class Property {
//
//    private Long id;
//
//    private String title;
//
//    private String propertyType;
//
//    private String guestSpace;
//
//    private Integer maxGuestNumber;
//
//    private Integer bedroomNumber;
//
//    private Integer bathNumber;
//
//    private Float pricePerNight;
//
//    private String country;
//
//    private String image;
//
//    private Set<AmenityDto> amenities;
//
//}