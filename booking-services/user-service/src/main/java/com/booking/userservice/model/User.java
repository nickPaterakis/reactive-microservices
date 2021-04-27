package com.booking.userservice.model;

import com.booking.bookingapi.core.user.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "users")
@Accessors( chain = true)
@Data
@NoArgsConstructor
public class User {

    @Id
    private UUID id;

    @Indexed
    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private List<Role> roles;

    @PersistenceConstructor
    public User(UUID id, String email, String firstName, String lastName, String phone, List<Role> roles) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.roles = roles;
    }
}
