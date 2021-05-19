package com.booking.propertyservice.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Table(name = "property_types")
public class PropertyType extends NameEntity {
    public PropertyType(String name) {
        super(name);
    }
}
