package com.booking.propertyservice.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "guest_spaces")
public class GuestSpace extends NameEntity {

    @OneToMany(mappedBy = "guestSpace", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Property> properties;

    public GuestSpace(String name) {
        super(name);
    }
}
