package com.booking.bookingapi.core.property;

import com.booking.bookingapi.composite.dto.BookingUser;
import com.booking.bookingapi.composite.dto.PropertyAggregate;
import com.booking.bookingapi.core.property.Dto.PageProperties;
import com.booking.bookingapi.core.property.Dto.PropertyDetailsDto;
import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface PropertyService {

    Mono<PageProperties> searchProperties(
            String location,
            LocalDate checkIn,
            LocalDate checkOut,
            int guestNumber,
            int currentPage
    );

    Mono<PageProperties> getProperties(@AuthenticationPrincipal BookingUser user);

    Mono<PropertyAggregate> getProperty(Long propertyId);

    Mono<Void> createProperty(PropertyDetailsDto propertyDetailsDto);

    Mono<Void> deleteProperty(Long id);

    default Mono<UserDetailsDto> findUserByEmail(String email){return null;};


}
