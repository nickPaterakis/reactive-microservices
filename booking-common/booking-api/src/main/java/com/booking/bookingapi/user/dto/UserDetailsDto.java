package com.booking.bookingapi.user.dto;

import com.booking.bookingapi.role.Role;
import com.booking.bookingapi.validationgroup.UpdateUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailsDto {

    @NotEmpty
    private String id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    @Null(groups = UpdateUser.class)
    private String email;

    private String phone;

    private String profileImage;

    private List<Role> roles;

    public UserDetailsDto( String id, String firstName, String lastName, String email, String phone, List<Role> roles, String profileImage) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
        this.profileImage = profileImage;

    }

    public UserDetailsDto(UserDetailsDto user) {
        this
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setPhone(user.getPhone())
                .setProfileImage(user.getProfileImage())
                .setRoles(user.getRoles());
    }
}
