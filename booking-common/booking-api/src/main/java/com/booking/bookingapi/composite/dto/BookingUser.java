package com.booking.bookingapi.composite.dto;

import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class BookingUser extends UserDetailsDto implements UserDetails {

  private static final String ROLE_PREFIX = "ROLE_";

  public BookingUser(UserDetailsDto user) {
    super(user);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
//    return getRoles().stream()
//        .map(Enum::name)
//        .map(String::toUpperCase)
//        .map(rn -> ROLE_PREFIX + rn)
//        .map(SimpleGrantedAuthority::new)
//        .collect(Collectors.toList());
    return null;
  }

  @Override
  public String getPassword() {
    return "n/a";
  }

  @Override
  public String getUsername() {
    return getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
