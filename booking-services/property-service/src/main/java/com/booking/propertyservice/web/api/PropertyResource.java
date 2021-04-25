package com.booking.propertyservice.web.api;

import com.booking.bookingapi.core.property.Dto.PropertyDto;
import com.booking.bookingapi.core.property.PropertyEndpoint;
import com.booking.bookingapi.core.property.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@RestController
public class PropertyResource implements PropertyEndpoint {

    
    private final PropertyService propertyService;

    @Autowired
    public PropertyResource(@Qualifier("PropertyServiceImpl") PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @Override
    public Flux<PropertyDto> searchProperties(
            String location,
            LocalDate checkIn,
            LocalDate checkOut,
            int guestNumber,
            int currentPage
            ) {
        return propertyService.searchProperties(location, checkIn, checkOut, guestNumber, currentPage);
    }

    @Override
    public Flux<PropertyDto> getProperties(String ownerId) {
        return propertyService.getProperties(ownerId);
    }

    //
//    @GetMapping("/{userId}")
//    public Set<PropertyDto> findByUser(@PathVariable("userId") Long userId) {
//        return propertyService.findByUser(userId);
//    }

}
