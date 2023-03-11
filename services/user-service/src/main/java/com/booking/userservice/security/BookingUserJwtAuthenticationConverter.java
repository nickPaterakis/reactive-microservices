package com.booking.userservice.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/** JWT converter that takes the roles from 'groups' claim of JWT token. */
public class BookingUserJwtAuthenticationConverter
    implements Converter<Jwt, Mono<AbstractAuthenticationToken>> {
  private static final String REALM_ACCESS = "realm_access";
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
            .switchIfEmpty(Mono.defer(() -> bookingReactiveUserDetailsService.saveUser(jwt)))
            .map(u -> new UsernamePasswordAuthenticationToken(u, "n/a", authorities));

    return abstractAuthenticationTokenMono;
  }

  private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
    return this.getRules(jwt).stream()
            .map(authority -> ROLE_PREFIX + authority.toUpperCase())
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
  }

  @SuppressWarnings("unchecked")
  private Collection<String> getRules(Jwt jwt){
    Map<String, Object> scopes = jwt.getClaim(REALM_ACCESS);
    if (scopes.get("roles") instanceof Collection) {
      return (Collection<String>) scopes.get("roles");
    }
    return Collections.emptyList();
  }
}
