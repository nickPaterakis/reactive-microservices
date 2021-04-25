package com.booking.bookingapi.core.property;

import com.booking.bookingapi.core.property.Dto.PropertyDto;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface PropertyService {

    Flux<PropertyDto> searchProperties(
            String location,
            LocalDate checkIn,
            LocalDate checkOut,
            int guestNumber,
            int currentPage
    );

    Flux<PropertyDto> getProperties(String ownerId);

//    Set<PropertyDto> findByUser(Long id);
}
