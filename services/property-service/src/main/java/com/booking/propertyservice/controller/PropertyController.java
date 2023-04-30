package com.booking.propertyservice.controller;

import com.booking.commondomain.dto.property.PageProperties;
import com.booking.commondomain.dto.property.PropertyAggregate;
import com.booking.commondomain.dto.property.PropertyDetailsDto;
import com.booking.commondomain.dto.property.PropertyReservationDataDto;
import com.booking.propertyservice.controller.request.PropertySearchCriteria;
import com.booking.propertyservice.service.propertyservice.PropertyService;
import com.booking.commondomain.dto.user.BookingUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
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
import java.util.UUID;

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

    // @PreAuthorize("hasRole('BOOKING_USER')")
    @GetMapping("/my-properties/{currentPage}")
    public Mono<PageProperties> getProperties(@AuthenticationPrincipal BookingUser user,
                                              @NotNull @Min(value = 0) @PathVariable int currentPage) {
        return propertyService.getProperties(user, currentPage);
    }

//    @PreAuthorize("hasRole('BOOKING_USER')")
//    @PostMapping(value = "/create-property", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
//    public Mono<Void> createProperty(@NotNull @RequestPart("file") Flux<FilePart> partFlux,
//                                     @NotEmpty @RequestPart("property") String property) throws JsonProcessingException {
//        PropertyDetailsDto propertyDetailsDto = objectMapper.readValue(property, PropertyDetailsDto.class);
//
//        String imagesURL = String.format("images/properties/%s/%s/%s", propertyDetailsDto.getCountry().getName(),
//                propertyDetailsDto.getOwnerId().toString(), UUID.randomUUID());
//
//        partFlux.doOnNext(fp -> propertyDetailsDto.getImages().add(imagesURL + "\\" + fp.filename())).zipWith(
//                partFlux.flatMap(Part::content),
//                (a, b) -> {
//                    final BlobId blobId = BlobId.of("booking-project",  imagesURL + "/" + a.filename());
//                    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
//                    byte[] bytes = b.asByteBuffer().array();
//                    storage.create(blobInfo, bytes);
//                    return Mono.empty();
//                }).then().subscribe();
//
//        return propertyService.createProperty(propertyDetailsDto);
//    }

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

    // @PreAuthorize("hasRole('BOOKING_USER')")
    @GetMapping("/property-reservation/{propertyId}")
    public Mono<PropertyReservationDataDto> getPropertyById(@NotNull @Positive @PathVariable Long propertyId) {
        return propertyService.getPropertyById(propertyId);
    }
}
