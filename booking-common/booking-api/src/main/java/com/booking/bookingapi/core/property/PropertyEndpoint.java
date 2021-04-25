package com.booking.bookingapi.core.property;

import com.booking.bookingapi.core.property.Dto.PropertyDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@RequestMapping("/properties")
public interface PropertyEndpoint extends PropertyService {

    @GetMapping("/search")
    @Override
    Flux<PropertyDto> searchProperties(
            @RequestParam(value = "location") String location,
            @RequestParam(value = "checkIn") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
            @RequestParam(value = "checkOut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
            @RequestParam(value = "guestNumber") int guestNumber,
            @RequestParam(value = "currentPage") int currentPage
    );

    @GetMapping("/{ownerId}")
    Flux<PropertyDto> getProperties(@PathVariable String ownerId);
}
