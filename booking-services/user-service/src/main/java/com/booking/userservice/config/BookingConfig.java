package com.booking.userservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

public class BookingConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }
//
//    @Bean
//    WebClient webClient(
//            ReactiveClientRegistrationRepository clientRegistrationRepository,
//            ServerOAuth2AuthorizedClientRepository authorizedClientRepository) {
//        ServerOAuth2AuthorizedClientExchangeFilterFunction oauth =
//                new ServerOAuth2AuthorizedClientExchangeFilterFunction(
//                        clientRegistrationRepository, authorizedClientRepository);
//        oauth.setDefaultOAuth2AuthorizedClient(true);
//        oauth.setDefaultClientRegistrationId("keycloak");
//        return WebClient.builder().filter(oauth).build();
//    }
}
