package com.booking.propertyservice.integration;

import com.booking.bookingapi.core.user.UserService;
import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import com.booking.bookingutils.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.UUID;

@EnableBinding(MessageSources.class)
@Component
@Log4j2
public class PropertyIntegration implements UserService {

    private final WebClient.Builder webClientBuilder;
    private final MessageSources messageSources;
    private final ObjectMapper mapper;
    private final String userServiceUrl;
    private WebClient webClient;

    @Autowired
    public PropertyIntegration(
            WebClient.Builder webClientBuilder,
            ObjectMapper mapper,
            MessageSources messageSources,
            @Value("${app.user-service.host}") String userServiceHost) {

        this.webClientBuilder = webClientBuilder;
        this.mapper = mapper;
        this.messageSources = messageSources;

        var http = "http://";

        userServiceUrl = http.concat(userServiceHost);
    }

    @Override
    public Mono<UserDetailsDto> findUserByEmail(String email) {
        var url = UriComponentsBuilder
                .fromUriString(userServiceUrl
                        .concat("/users/{email}"))
                .build(email);

        //.onErrorMap(WebClientResponseException.class, ex -> new NotFoundException(ex.getMessage()));

        return getWebClient()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(UserDetailsDto.class)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<UserDetailsDto> getUserDetails(UUID uuid) {
        var url = UriComponentsBuilder
//                .fromUriString("http://localhost:8082"
                .fromUriString(userServiceUrl
                        .concat("/users/me/{uuid}"))
                .build(uuid.toString());
        System.out.println(uuid.toString());
        return getWebClient()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(UserDetailsDto.class)
                .onErrorMap(WebClientResponseException.class, ex -> new NotFoundException(ex.getMessage()));
    }

    private WebClient getWebClient() {
        if (webClient == null) {
            webClient = webClientBuilder.build();
        }
        return webClient;
    }
}
