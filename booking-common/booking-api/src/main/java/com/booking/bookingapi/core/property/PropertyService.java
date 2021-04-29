package com.booking.bookingapi.core.property;

import com.booking.bookingapi.core.property.Dto.PageProperties;
import com.booking.bookingapi.core.property.Dto.PropertyDetailsDto;
import com.booking.bookingapi.core.property.Dto.PropertyDto;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

public interface PropertyService {

    Mono<PageProperties> searchProperties(
            String location,
            LocalDate checkIn,
            LocalDate checkOut,
            int guestNumber,
            int currentPage
    );

    Mono<PageProperties> getProperties(UUID ownerId);

    Mono<PropertyDetailsDto> getProperty(Long propertyId);

//    Set<PropertyDto> findByUser(Long id);
}
