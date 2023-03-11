package com.booking.propertyservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "guest_spaces")
public class GuestSpace extends NameEntity {

    @OneToMany(mappedBy = "guestSpace", cascade = CascadeType.ALL)
    private List<Property> properties = new ArrayList<>();

    public GuestSpace(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public void addProperty(Property property) {
        this.properties.add(property);
        property.setGuestSpace(this);
    }
}
