package com.booking.propertyservice.service.propertyservice;


import com.booking.commondomain.dto.property.PropertyReservationDataDto;
import com.booking.commondomain.dto.user.BookingUser;
import com.booking.commondomain.dto.property.PropertyAggregate;
import com.booking.commondomain.dto.property.PageProperties;
import com.booking.propertyservice.controller.request.PropertySearchCriteria;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PropertyService {

    Mono<PageProperties> searchProperties(PropertySearchCriteria criteria);

    Mono<PageProperties> getProperties(@AuthenticationPrincipal BookingUser user, int currentPage);

    Mono<PropertyAggregate> getProperty(Long propertyId);

    Mono<Void> createProperty(Flux<FilePart> filePartFlux, String propertyDetailsDto);

    Mono<PropertyReservationDataDto> getPropertyById(Long propertyId);

    Mono<Void> deleteProperty(Long id);
}
