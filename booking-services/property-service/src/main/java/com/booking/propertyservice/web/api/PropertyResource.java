package com.booking.propertyservice.web.api;

import com.booking.bookingapi.property.Dto.PageProperties;
import com.booking.bookingapi.property.Dto.PropertyAggregate;
import com.booking.bookingapi.property.Dto.PropertyDetailsDto;
import com.booking.bookingapi.property.Dto.PropertyReservationDataDto;
import com.booking.bookingapi.property.PropertyService;
import com.booking.bookingapi.property.validationgroup.CreateProperty;
import com.booking.bookingapi.user.dto.BookingUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@CrossOrigin("*")
@RequestMapping("/properties")
@RestController
@Validated
public class PropertyResource {

    private final PropertyService propertyService;
    private final ObjectMapper objectMapper;
    private final Validator validator;
    private final Path basePath = Paths.get("C:\\Users\\Nicholas\\Documents\\Projects\\Spring\\booking\\booking-services\\property-service\\src\\main\\resources\\images");


    @Autowired
    public PropertyResource(@Qualifier("PropertyServiceImpl") PropertyService propertyService,
                            ObjectMapper objectMapper,
                            Validator validator) {
        this.propertyService = propertyService;
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    @GetMapping("/search")
    public Mono<PageProperties> searchProperties(
            @NotEmpty @RequestParam(value = "location") String location,
            @NotNull @RequestParam(value = "checkIn") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
            @NotNull @RequestParam(value = "checkOut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
            @NotNull @Range(min = 0, max = 16) @RequestParam(value = "guestNumber") int guestNumber,
            @NotNull @Min(value = 0) @RequestParam(value = "currentPage") int currentPage
            ) {
        return propertyService.searchProperties(location, checkIn, checkOut, guestNumber, currentPage);
    }

    @GetMapping("/{currentPage}")
    public Mono<PageProperties> getProperties(@AuthenticationPrincipal BookingUser user,
                                              @NotNull @Min(value = 0) @PathVariable int currentPage) {
        return propertyService.getProperties(user, currentPage);
    }

    @GetMapping("/property/{propertyId}")
    public Mono<PropertyAggregate> getProperty(@NotNull @PathVariable Long propertyId) {
        return propertyService.getProperty(propertyId);
    }

    @PostMapping(value = "/create-property", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @Validated(CreateProperty.class)
    public Mono<Void> createProperty(@NotNull @RequestPart("file") Flux<FilePart> partFlux,
                                     @NotEmpty @RequestPart("property") String property) throws JsonProcessingException {

        PropertyDetailsDto propertyDetailsDto = objectMapper.readValue(property, PropertyDetailsDto.class);

        System.out.println(propertyDetailsDto);
        Set<ConstraintViolation<PropertyDetailsDto>> violations = validator.validate(propertyDetailsDto, CreateProperty.class);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        String imagesURL = "\\" + propertyDetailsDto.getCountry().getName() + "\\" +
                propertyDetailsDto.getOwnerId().toString() + "\\" + UUID.randomUUID().toString();

        File directory = new File(basePath.toString() + imagesURL);
        if (!directory.exists()){
            directory.mkdirs();
        }
        partFlux
                .doOnNext(fp -> propertyDetailsDto.getImages().add(imagesURL + "\\" + fp.filename()))
                .flatMap(fp -> fp.transferTo(Path.of(directory.toString() + "\\" + fp.filename())))
                .then().subscribe();

        return propertyService.createProperty(propertyDetailsDto);
    }

    @DeleteMapping("delete/{id}")
    public Mono<Void> deleteProperty(@NotNull @Positive @PathVariable Long id) {
        return propertyService.deleteProperty(id);
    }

    @GetMapping("/property-reservation/{propertyId}")
    public Mono<PropertyReservationDataDto> getPropertyById(@NotNull @Positive @PathVariable Long propertyId) {
        return propertyService.getPropertyById(propertyId);
    }
}
