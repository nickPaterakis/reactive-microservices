package com.booking.propertyservice.service.propertyservice;


import com.booking.commondomain.dto.property.PropertyReservationDataDto;
import com.booking.commondomain.dto.user.BookingUser;
import com.booking.commondomain.dto.property.PropertyAggregate;
import com.booking.commondomain.dto.property.PageProperties;
import com.booking.commondomain.dto.property.PropertyDetailsDto;
import com.booking.propertyservice.controller.request.PropertySearchCriteria;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface PropertyService {

    Mono<PageProperties> searchProperties(PropertySearchCriteria criteria);

    Mono<PageProperties> getProperties(@AuthenticationPrincipal BookingUser user, int currentPage);

    Mono<PropertyAggregate> getProperty(Long propertyId);

    Mono<Void> createProperty(PropertyDetailsDto propertyDetailsDto);

    Mono<PropertyReservationDataDto> getPropertyById(Long propertyId);

    Mono<Void> deleteProperty(Long id);
}
