package com.booking.propertyservice.integration.userservice;

import com.booking.bookingutils.exception.NotFoundException;
import com.booking.commondomain.dto.user.UserDetailsDto;
import com.booking.commondomain.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserServiceIntegrationImpl implements UserServiceIntegration {

    @Value("${property-service.user-service.url}")
    private String userServiceUrl;
    private final WebClient webClient;

    @Override
    public Mono<UserDetailsDto> findUserByEmail(String email) {
        var url = UriComponentsBuilder.fromUriString(userServiceUrl.concat("/users/{email}"))
                .build(email);

        return webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(UserDetailsDto.class)
                .onErrorMap(WebClientResponseException.class,
                        ex -> new NotFoundException("User with email: " + email + " doesn't exist"));
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
                .onErrorMap(WebClientResponseException.class, ex -> new NotFoundException(ex.getMessage()));
    }
}
