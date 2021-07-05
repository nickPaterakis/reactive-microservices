package com.booking.bookingapi.property;

import com.booking.bookingapi.property.Dto.PropertyReservationDataDto;
import com.booking.bookingapi.user.dto.BookingUser;
import com.booking.bookingapi.property.Dto.PropertyAggregate;
import com.booking.bookingapi.property.Dto.PageProperties;
import com.booking.bookingapi.property.Dto.PropertyDetailsDto;
import com.booking.bookingapi.user.dto.UserDetailsDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface PropertyService {

    default Mono<PageProperties> searchProperties(
            String location,
            LocalDate checkIn,
            LocalDate checkOut,
            int guestNumber,
            int currentPage
    ){return null;}

    default Mono<PageProperties> getProperties(@AuthenticationPrincipal BookingUser user, int currentPage) {return null;}

    default Mono<PropertyAggregate> getProperty(Long propertyId){return null;}

    default Mono<Void> createProperty(PropertyDetailsDto propertyDetailsDto){return null;}

    default Mono<Void> deleteProperty(Long id){return null;}

    default Mono<UserDetailsDto> findUserByEmail(String email){return null;}

    Mono<PropertyReservationDataDto> getPropertyById(Long propertyId);


}
