package com.booking.propertyservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "amenities")
public class Amenity extends NameEntity {

    @ManyToMany(mappedBy = "amenities", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Property> properties;

    public Amenity(String name) {
        super(name);
    }
}
