package com.booking.propertyservice.service.propertyservice;

import com.booking.bookingutils.exception.InvalidInputException;
import com.booking.commondomain.dto.property.AddressDto;
import com.booking.commondomain.dto.property.PropertyAggregate;
import com.booking.commondomain.dto.property.PropertyDetailsDto;
import com.booking.commondomain.dto.property.PropertyDto;
import com.booking.commondomain.dto.user.UserDto;
import com.booking.propertyservice.integration.reservationservice.ReservationServiceIntegration;
import com.booking.propertyservice.mapper.PropertyMapper;
import com.booking.propertyservice.model.Property;
import com.booking.propertyservice.repository.PropertyRepository;
import com.booking.propertyservice.utils.ReactiveUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.domain.Pageable;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class PropertyServiceHelper {

    private final ReservationServiceIntegration reservationServiceIntegration;
    private final PropertyRepository propertyRepository;
    private final Storage storage;
    private final ReactiveUtils reactiveUtils;
    @Value("${property-service.bucket-name}")
    private String bucketName;

    public Mono<List<Long>> getPropertyIds(String location, LocalDate checkIn, LocalDate checkOut) {
        return reservationServiceIntegration.getPropertyIds(location, checkIn, checkOut)
                .collectList();
    }

    public List<Long> preparePropertyIds(List<Long> propertyIds) {
        List<Long> ids = new ArrayList<>(propertyIds);
        ids.add(0L);
        return ids;
    }

    public int countTotalElements(List<Long> propertyIds, String location, int guestNumber) {
        return propertyRepository.count(propertyIds, location, guestNumber);
    }

    public Mono<List<PropertyDto>> getProperties(List<Long> propertyIds,
                                                 String location,
                                                 int guestNumber,
                                                 Pageable pageable) {
        return Flux.fromIterable(
                propertyRepository.searchProperties(propertyIds, location, guestNumber, pageable))
                .map(PropertyMapper::toPropertyDto)
                .collectList();
    }

    public PropertyAggregate setPropertyAggregateFromDto(PropertyAggregate propertyAggregate,
                                                         PropertyDetailsDto propertyDetailsDto) {
        return propertyAggregate
                .setId(propertyDetailsDto.getId())
                .setTitle(propertyDetailsDto.getTitle())
                .setPropertyType(propertyDetailsDto.getPropertyType().getName())
                .setGuestSpace(propertyDetailsDto.getGuestSpace().getName())
                .setMaxGuestNumber(propertyDetailsDto.getMaxGuestNumber())
                .setBedroomNumber(propertyDetailsDto.getBedroomNumber())
                .setBathNumber(propertyDetailsDto.getBathNumber())
                .setPricePerNight(propertyDetailsDto.getPricePerNight())
                .setAddress(new AddressDto(
                        propertyDetailsDto.getCity(),
                        propertyDetailsDto.getCountry().getName(),
                        propertyDetailsDto.getPostCode(),
                        propertyDetailsDto.getStreetName(),
                        propertyDetailsDto.getStreetNumber()
                ))
                .setDescription(propertyDetailsDto.getDescription())
                .setImages(propertyDetailsDto.getImages())
                .setAmenities(propertyDetailsDto.getAmenities())
                .setOwnerId(propertyDetailsDto.getOwnerId());
    }

    public void setOwnerDetails(PropertyAggregate propertyAggregate, UserDto userDto) {
        propertyAggregate
                .setOwnerFirstName(userDto.getFirstName())
                .setOwnerLastName(userDto.getLastName())
                .setOwnerImage(userDto.getImage());
    }

    public Property mapPropertyDetailsToProperty(PropertyDetailsDto propertyDetailsDto) {
        Property property = PropertyMapper.toProperty(propertyDetailsDto);
        property.getPropertyType().addProperty(property);
        property.getGuestSpace().addProperty(property);
        property.getAddress().getCountry().addAddress(property.getAddress());
        return property;
    }

    public Mono<Property> saveProperty(Property property) {
        return reactiveUtils.asyncMono(() -> Mono.just(propertyRepository.save(property)));
    }

    public PropertyDetailsDto deserializePropertyDetails(String property) {
        ObjectMapper objectMapper = new ObjectMapper();
        PropertyDetailsDto propertyDetailsDto;

        try {
            propertyDetailsDto = objectMapper.readValue(property, PropertyDetailsDto.class);
        } catch (JsonProcessingException e) {
            throw new InvalidInputException("Invalid JSON", e);
        }

        log.debug("Deserialized property details: {}", propertyDetailsDto);
        return propertyDetailsDto;
    }

    public void processAndUploadImages(Flux<FilePart> partFlux, PropertyDetailsDto propertyDetailsDto) {
        String imagesURL = generateImagesURL(propertyDetailsDto);
        log.debug("Generated images URL: {}", imagesURL);

        partFlux.doOnNext(fp -> propertyDetailsDto.getImages().add(imagesURL + "\\" + fp.filename()))
                .zipWith(
                partFlux.flatMap(Part::content),
                (a, b) -> {
                    uploadImageToStorage(imagesURL, a, b);
                    return Mono.empty();
                }).then().subscribe();
    }

    private String generateImagesURL(PropertyDetailsDto propertyDetailsDto) {
        return String.format("images/properties/%s/%s/%s", propertyDetailsDto.getCountry().getName(),
                propertyDetailsDto.getOwnerId().toString(), UUID.randomUUID());
    }

    private void uploadImageToStorage(String imagesURL, FilePart filePart, DataBuffer dataBuffer) {
        final BlobId blobId = BlobId.of(bucketName, imagesURL + "/" + filePart.filename());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        byte[] bytes = dataBuffer.asByteBuffer().array();
        storage.create(blobInfo, bytes);
        log.debug("Uploaded image '{}' to storage", filePart.filename());
    }
}
