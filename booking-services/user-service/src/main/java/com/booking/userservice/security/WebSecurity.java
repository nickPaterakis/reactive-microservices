package com.booking.userservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurity {

    private final BookingReactiveUserDetailsService bookingReactiveUserDetailsService;

    @Autowired
    public WebSecurity(BookingReactiveUserDetailsService bookingReactiveUserDetailsService) {
        this.bookingReactiveUserDetailsService = bookingReactiveUserDetailsService;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http)  {
        http
                .cors()
                .disable()
                .csrf()
                .disable()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(bookingUserJwtAuthenticationConverter());

        return http.build();
    }
//
//    @Bean
//    SecurityWebFilterChain configure(ServerHttpSecurity http) {
//        http.authorizeExchange().anyExchange().authenticated().and().oauth2Client().and().oauth2Login();
//        return http.build();
//    }

    @Bean
    public BookingUserJwtAuthenticationConverter bookingUserJwtAuthenticationConverter() {
        return new BookingUserJwtAuthenticationConverter(bookingReactiveUserDetailsService);
    }
}
