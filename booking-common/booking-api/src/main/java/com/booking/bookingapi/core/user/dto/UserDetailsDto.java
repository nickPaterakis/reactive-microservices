package com.booking.bookingapi.core.user.dto;

import com.booking.bookingapi.core.user.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailsDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<Role> roles;


    public UserDetailsDto(UUID id, String firstName, String lastName, String email, String phone, List<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
    }

    public UserDetailsDto(UserDetailsDto user) {
        this
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setPhone(user.getPhone())
                .setRoles(user.getRoles());
    }
}
