package com.booking.bookingservice.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import reactor.core.publisher.Flux;

import java.util.Map;

public class KeycloakRuleConverter implements Converter<Jwt, Flux<GrantedAuthority>> {

    @Override
    public Flux<GrantedAuthority> convert(Jwt jwt) {
        Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");

        if (realmAccess == null || realmAccess.isEmpty()) {
            return null;
        }
        //Flux<GrantedAuthority> returnValue =  Flux.just((List<String>) realmAccess.get("roles"));
//                .stream().map(roleName -> "ROLE_" + roleName)
//                .map(SimpleGrantedAuthority::new));

        return Flux.just(realmAccess.get("roles"))
                .map(roleName -> "ROLE_" + roleName)
                .map(SimpleGrantedAuthority::new);
     }
}
