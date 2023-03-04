package com.booking.propertyservice.service;


import com.booking.commondomain.dto.property.PropertyReservationDataDto;
import com.booking.commondomain.dto.user.BookingUser;
import com.booking.commondomain.dto.property.PropertyAggregate;
import com.booking.commondomain.dto.property.PageProperties;
import com.booking.commondomain.dto.property.PropertyDetailsDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface PropertyService {

    Mono<PageProperties> searchProperties(
            String location,
            LocalDate checkIn,
            LocalDate checkOut,
            int guestNumber,
            int currentPage);

    Mono<PageProperties> getProperties(@AuthenticationPrincipal BookingUser user, int currentPage);

    Mono<PropertyAggregate> getProperty(Long propertyId);

    Mono<Void> createProperty(PropertyDetailsDto propertyDetailsDto);

    Mono<PropertyReservationDataDto> getPropertyById(Long propertyId);

    void deleteProperty(Long id);
}
