package com.booking.propertyservice.integration;

import com.booking.bookingapi.event.Event;
import com.booking.bookingapi.reservation.ReservationService;
import com.booking.bookingapi.reservation.dto.ReservationDto;
import com.booking.bookingapi.user.UserService;
import com.booking.bookingapi.user.dto.UserDetailsDto;
import com.booking.bookingapi.user.dto.UserDto;
import com.booking.bookingutils.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

import static com.booking.bookingapi.event.Event.Type.CREATE;
import static com.booking.bookingapi.event.Event.Type.DELETE;

@EnableBinding(MessageSources.class)
@Component
@Log4j2
public class PropertyIntegration implements UserService, ReservationService {

    private final WebClient.Builder webClientBuilder;
    private final MessageSources messageSources;
    private final ObjectMapper mapper;
    private final String userServiceUrl;
    private final String reservationServiceUrl;
    private WebClient webClient;

    @Autowired
    public PropertyIntegration(
            WebClient.Builder webClientBuilder,
            ObjectMapper mapper,
            MessageSources messageSources,
            @Value("${app.user-service.host}") String userServiceHost,
            @Value("${app.user-service.port}") String userServicePort,
            @Value("${app.reservation-service.host}") String reservationServiceHost,
            @Value("${app.reservation-service.port}") String reservationServicePort) {

        this.webClientBuilder = webClientBuilder;
        this.mapper = mapper;
        this.messageSources = messageSources;

        var http = "http://";

        userServiceUrl = http.concat(userServiceHost + ":" + userServicePort);
        reservationServiceUrl = http.concat(reservationServiceHost + ":" + reservationServicePort);
    }

    @Override
    public Mono<UserDetailsDto> findUserByEmail(String email) {
        var url = UriComponentsBuilder
                .fromUriString(userServiceUrl
                        .concat("/users/{email}"))
                .build(email);

        return getWebClient()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(UserDetailsDto.class)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Flux<Long> getPropertyIds(String location, LocalDate checkIn, LocalDate checkOut) {
        var url = UriComponentsBuilder
                .fromUriString(reservationServiceUrl
                        .concat("/reservations/propertyIds?location={location}&checkIn={checkIn}&checkOut={checkOut}"))
                .build(location, checkIn, checkOut);

        return getWebClient()
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(Long.class)
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<Void> createReservation(ReservationDto reservationDto) {

        Message<Event<Long, ReservationDto>> message =
                MessageBuilder.withPayload(new Event<>(CREATE, reservationDto.getPropertyId(), reservationDto))
                .setHeader("type", 1).build();

        messageSources
                .outputReservations()
                .send(message);

        return Mono.empty();
    }

    @Override
    public Mono<Void> deleteAllReservationsByPropertyId(Long propertyId) {
        log.info("Delete all reservations by property id: {}", propertyId);

        Message<Event<Long, Long>> message =
                MessageBuilder.withPayload(new Event<>(DELETE, propertyId, propertyId))
                        .setHeader("type", 2).build();
        messageSources
                .outputReservations()
                .send(message);

        return Mono.empty();
    }

    @Override
    public Mono<UserDto> getUserById(UUID userId) {
        var url = UriComponentsBuilder
                .fromUriString(userServiceUrl
                        .concat("/users/user-id/{userId}"))
                .build(userId.toString());

        return getWebClient()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(UserDto.class)
                .onErrorMap(WebClientResponseException.class, ex -> new NotFoundException(ex.getMessage()));
    }

    private WebClient getWebClient() {
        if (webClient == null) {
            webClient = webClientBuilder.build();
        }
        return webClient;
    }
}
