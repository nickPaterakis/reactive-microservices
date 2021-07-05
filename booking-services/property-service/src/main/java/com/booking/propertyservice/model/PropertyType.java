package com.booking.propertyservice.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "property_types")
public class PropertyType extends NameEntity {

    @OneToMany(mappedBy = "propertyType", cascade = CascadeType.ALL)
    private List<Property> properties = new ArrayList<>();

    public PropertyType(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public void addProperty(Property property) {
        this.properties.add(property);
        property.setPropertyType(this);
    }

    @Override
    public String toString() {
        return "PropertyType{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
