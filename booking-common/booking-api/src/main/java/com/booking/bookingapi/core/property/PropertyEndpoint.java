package com.booking.bookingapi.core.property;

import com.booking.bookingapi.composite.dto.BookingUser;
import com.booking.bookingapi.composite.dto.PropertyAggregate;
import com.booking.bookingapi.core.property.Dto.PageProperties;
import com.booking.bookingapi.core.property.Dto.PropertyDetailsDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RequestMapping("/properties")
public interface PropertyEndpoint extends PropertyService {

    @GetMapping("/search")
    @Override
    Mono<PageProperties> searchProperties(
            @RequestParam(value = "location") String location,
            @RequestParam(value = "checkIn") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
            @RequestParam(value = "checkOut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
            @RequestParam(value = "guestNumber") int guestNumber,
            @RequestParam(value = "currentPage") int currentPage
    );

    @GetMapping("/{ownerId}")
    @Override
    Mono<PageProperties> getProperties(@AuthenticationPrincipal BookingUser user);

    @GetMapping("/property/{propertyId}")
    @Override
    Mono<PropertyAggregate> getProperty(@PathVariable Long propertyId);

    @PostMapping("create")
    Mono<Void> createProperty(@RequestBody PropertyDetailsDto propertyDetailsDto);

    @DeleteMapping("delete/{id}")
    Mono<Void> deleteProperty(@PathVariable Long id);
}
