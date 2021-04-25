package com.booking.bookingservice.config;

import com.booking.bookingservice.security.BookingReactiveUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class WebSecurity {

    BookingReactiveUserDetailsService bookingReactiveUserDetailsService;

    @Autowired
    public WebSecurity(BookingReactiveUserDetailsService bookingReactiveUserDetailsService) {
        this.bookingReactiveUserDetailsService = bookingReactiveUserDetailsService;
    }

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http)  {
        http
                .cors()
                .disable()
                .csrf()
                .disable()
                .authorizeExchange()
                .pathMatchers("/booking/api/v1/countries/**").permitAll()
                .pathMatchers("/booking/api/v1/properties/**").permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(bookingUserJwtAuthenticationConverter());

        return http.build();
    }

    @Bean
    public BookingUserJwtAuthenticationConverter bookingUserJwtAuthenticationConverter() {
        return new BookingUserJwtAuthenticationConverter(bookingReactiveUserDetailsService);
    }
}
