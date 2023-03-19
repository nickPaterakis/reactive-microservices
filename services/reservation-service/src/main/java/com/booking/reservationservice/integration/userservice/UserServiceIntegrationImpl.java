package com.booking.reservationservice.integration.userservice;

import com.booking.commondomain.dto.user.UserDetailsDto;
import com.booking.commondomain.dto.user.UserDto;
import com.booking.reservationservice.integration.propertyservice.MessageSources;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@EnableBinding(MessageSources.class)
@RequiredArgsConstructor
public class UserServiceIntegrationImpl implements UserServiceIntegration {

    @Value("${reservation-service.user-service.url}")
    private String userServiceUrl;
    private final WebClient webClient;

    @Override
    public Mono<UserDetailsDto> findUserByEmail(String email) {
        var url = UriComponentsBuilder
                .fromUriString(userServiceUrl.concat("/users/{email}"))
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
        var url = UriComponentsBuilder.fromUriString(userServiceUrl.concat("/users/user-id/{userId}"))
                .build(userId.toString());

        return webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(UserDto.class)
                .switchIfEmpty(Mono.empty());
    }
}
