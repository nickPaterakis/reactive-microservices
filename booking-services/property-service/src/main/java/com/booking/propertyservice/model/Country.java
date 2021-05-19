package com.booking.propertyservice.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Table(name = "countries")
public class Country extends NameEntity {

    public Country(String name) {
        super(name);
    }
}
