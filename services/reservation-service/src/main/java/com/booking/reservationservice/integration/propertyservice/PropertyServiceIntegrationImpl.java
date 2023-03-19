package com.booking.reservationservice.integration.propertyservice;

import com.booking.commondomain.dto.property.PropertyReservationDataDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class PropertyServiceIntegrationImpl implements PropertyServiceIntegration {

    @Value("${reservation-service.property-service.url}")
    private String propertyServiceUrl;
    private final WebClient webClient;

    @Override
    public Mono<PropertyReservationDataDto> getPropertyById(Long propertyId) {
        var url = UriComponentsBuilder
                .fromUriString(propertyServiceUrl
                        .concat("/properties/property-reservation/{propertyId}"))
                .build(propertyId);

        return webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(PropertyReservationDataDto.class)
                .switchIfEmpty(Mono.empty());
    }
}
