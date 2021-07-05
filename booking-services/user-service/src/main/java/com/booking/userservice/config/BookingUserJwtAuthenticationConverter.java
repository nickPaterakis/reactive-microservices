package com.booking.userservice.config;

import com.booking.userservice.security.BookingReactiveUserDetailsService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/** JWT converter that takes the roles from 'groups' claim of JWT token. */
public class BookingUserJwtAuthenticationConverter
    implements Converter<Jwt, Mono<AbstractAuthenticationToken>> {
  private static final String GROUPS_CLAIM = "groups";
  private static final String ROLE_PREFIX = "ROLE_";

  private final BookingReactiveUserDetailsService bookingReactiveUserDetailsService;

  public BookingUserJwtAuthenticationConverter(BookingReactiveUserDetailsService bookingReactiveUserDetailsService) {
    this.bookingReactiveUserDetailsService = bookingReactiveUserDetailsService;
  }

  @Override
  public Mono<AbstractAuthenticationToken> convert(Jwt jwt) {
    Collection<GrantedAuthority> authorities = extractAuthorities(jwt);
    Mono<AbstractAuthenticationToken> abstractAuthenticationTokenMono =  bookingReactiveUserDetailsService
            .findByUsername(jwt.getClaimAsString("email"))
            //.onErrorResume(e -> bookingReactiveUserDetailsService.saveUser(jwt))
            .switchIfEmpty(bookingReactiveUserDetailsService.saveUser(jwt))
            .map(u -> new UsernamePasswordAuthenticationToken(u, "n/a", authorities));

    return abstractAuthenticationTokenMono;
  }

  private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
    return this.getScopes(jwt).stream()
        .map(authority -> ROLE_PREFIX + authority.toUpperCase())
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }

  @SuppressWarnings("unchecked")
  private Collection<String> getScopes(Jwt jwt) {
    Object scopes = jwt.getClaims().get(GROUPS_CLAIM);
    if (scopes instanceof Collection) {
      return (Collection<String>) scopes;
    }

    return Collections.emptyList();
  }
}
