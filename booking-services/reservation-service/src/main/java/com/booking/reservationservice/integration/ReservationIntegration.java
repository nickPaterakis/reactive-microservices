package com.booking.reservationservice.integration;

import com.booking.bookingapi.property.Dto.PropertyReservationDataDto;
import com.booking.bookingapi.property.PropertyService;
import com.booking.bookingapi.user.UserService;
import com.booking.bookingapi.user.dto.UserDetailsDto;
import com.booking.bookingapi.user.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.UUID;

@EnableBinding(MessageSources.class)
@Component
@Log4j2
public class ReservationIntegration implements UserService, PropertyService {

    private final WebClient.Builder webClientBuilder;
    private final MessageSources messageSources;
    private final ObjectMapper mapper;
    private final String userServiceUrl;
    private final String propertyServiceUrl;
    private final WebClient webClient;

    @Autowired
    public ReservationIntegration(
            WebClient.Builder webClientBuilder,
            WebClient webClient,
            ObjectMapper mapper,
            MessageSources messageSources,
            @Value("${app.user-service.host}") String userServiceHost,
            @Value("${app.user-service.port}") String userServicePort,
            @Value("${app.property-service.host}") String propertyServiceHost,
            @Value("${app.property-service.port}") String propertyServicePort) {
        this.webClient = webClient;
        this.webClientBuilder = webClientBuilder;
        this.mapper = mapper;
        this.messageSources = messageSources;

        var http = "http://";

        userServiceUrl = http.concat(userServiceHost + ":" + userServicePort);
        propertyServiceUrl = http.concat(propertyServiceHost + ":" + propertyServicePort);
    }

    @Override
    public Mono<UserDetailsDto> findUserByEmail(String email) {
        var url = UriComponentsBuilder
                .fromUriString(userServiceUrl
                        .concat("/users/{email}"))
                .build(email);

        return webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(UserDetailsDto.class)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<UserDto> getUserById(UUID userId) {
        var url = UriComponentsBuilder
                .fromUriString(userServiceUrl
                        .concat("/users/user-id/{userId}"))
                .build(userId.toString());

        return webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(UserDto.class)
                .switchIfEmpty(Mono.empty());
    }

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

//    private WebClient getWebClient() {
//        if (webClient == null) {
//            webClient = webClientBuilder.build();
//        }
//        return webClient;
//    }
}
