package com.booking.propertyservice.controller;

import com.booking.commondomain.dto.property.PageProperties;
import com.booking.commondomain.dto.property.PropertyAggregate;
import com.booking.commondomain.dto.property.PropertyReservationDataDto;
import com.booking.propertyservice.controller.request.PropertySearchCriteria;
import com.booking.propertyservice.service.propertyservice.PropertyService;
import com.booking.commondomain.dto.user.BookingUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@CrossOrigin("*")
@RequestMapping("/properties")
@RestController
@Validated
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping("/search")
    public Mono<PageProperties> searchProperties(@Valid PropertySearchCriteria criteria) {
        return propertyService.searchProperties(criteria);
    }

    @GetMapping("/property/{propertyId}")
    public Mono<PropertyAggregate> getProperty(@NotNull @PathVariable Long propertyId) {
        return propertyService.getProperty(propertyId);
    }

    @PreAuthorize("hasRole('BOOKING_USER')")
    @GetMapping("/my-properties/{currentPage}")
    public Mono<PageProperties> getProperties(@AuthenticationPrincipal BookingUser user,
                                              @NotNull @Min(value = 0) @PathVariable int currentPage) {
        return propertyService.getProperties(user, currentPage);
    }

    @PreAuthorize("hasRole('BOOKING_USER')")
    @PostMapping(value = "/create-property", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Mono<Void> createProperty(@NotNull @RequestPart("file") Flux<FilePart> filePartFlux,
                                     @NotEmpty @RequestPart("property") String property) {
        return propertyService.createProperty(filePartFlux, property);
    }

    @PreAuthorize("hasRole('BOOKING_USER')")
    @DeleteMapping("delete/{id}")
    public Mono<Void> deleteProperty(@NotNull @Positive @PathVariable Long id) {
        return propertyService.deleteProperty(id);
    }

    @GetMapping("/property-reservation/{propertyId}")
    public Mono<PropertyReservationDataDto> getPropertyById(@NotNull @Positive @PathVariable Long propertyId) {
        return propertyService.getPropertyById(propertyId);
    }
}
