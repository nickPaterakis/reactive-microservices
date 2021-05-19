package com.booking.propertyservice.web.api;

import com.booking.bookingapi.core.property.Dto.PageProperties;
import com.booking.bookingapi.core.property.Dto.PropertyDetailsDto;
import com.booking.bookingapi.core.property.PropertyEndpoint;
import com.booking.bookingapi.core.property.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@RestController
public class PropertyResource implements PropertyEndpoint {

    private final PropertyService propertyService;

    @Autowired
    public PropertyResource(@Qualifier("PropertyServiceImpl") PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @Override
    public Mono<PageProperties> searchProperties(
            String location,
            LocalDate checkIn,
            LocalDate checkOut,
            int guestNumber,
            int currentPage
            ) {
        return propertyService.searchProperties(location, checkIn, checkOut, guestNumber, currentPage);
    }

    @Override
    public Mono<PageProperties> getProperties(UUID ownerId) {
        return propertyService.getProperties(ownerId);
    }

    @Override
    public Mono<PropertyDetailsDto> getProperty(Long propertyId) {
        return propertyService.getProperty(propertyId);
    }

}
