package com.booking.propertyservice.integration.reservationservice;

import com.booking.commondomain.event.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

import static com.booking.commondomain.event.Event.Type.DELETE;

@Slf4j
@Component
@EnableBinding(MessageSources.class)
@RequiredArgsConstructor
public class ReservationServiceIntegrationImpl implements ReservationServiceIntegration {

    private final MessageSources messageSources;
    @Value("${property-service.reservation-service.url}")
    private String reservationServiceUrl;
    private final WebClient webClient;

    @Override
    public Flux<Long> getPropertyIds(String location, LocalDate checkIn, LocalDate checkOut) {
        var url = UriComponentsBuilder
                .fromUriString(reservationServiceUrl)
                .path("/reservations/propertyIds")
                .queryParam("location", location)
                .queryParam("checkIn", checkIn)
                .queryParam("checkOut", checkOut)
                .build();

        log.debug("Fetching property IDs with URL: {}", url);

        return webClient
                .get()
                .uri(url.toUri())
                .retrieve()
                .bodyToFlux(Long.class)
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public void deleteAllReservationsByPropertyId(Long propertyId) {
        log.info("Deleting all reservations for property with ID: {}", propertyId);

        Event<Long, Long> event = new Event<>(DELETE, propertyId, propertyId);

        messageSources.outputReservations().send(MessageBuilder.withPayload(event).build());
    }
}
