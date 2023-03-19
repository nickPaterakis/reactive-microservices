package com.booking.reservationservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
public class WebSecurity {

    private final BookingReactiveUserDetailsService bookingReactiveUserDetailsService;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http)  {
        http
                .cors()
                .disable()
                .csrf()
                .disable()
                .authorizeExchange()
                .pathMatchers("/reservations/**").permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(bookingUserJwtAuthenticationConverter());

        return http.build();
    }

    @Bean
    SecurityWebFilterChain configure(ServerHttpSecurity http) {
        http.authorizeExchange().anyExchange().authenticated().and().oauth2Client().and().formLogin();
        return http.build();
    }

    @Bean
    public BookingUserJwtAuthenticationConverter bookingUserJwtAuthenticationConverter() {
        return new BookingUserJwtAuthenticationConverter(bookingReactiveUserDetailsService);
    }
}
