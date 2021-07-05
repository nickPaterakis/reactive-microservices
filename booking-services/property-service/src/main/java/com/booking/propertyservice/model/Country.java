package com.booking.propertyservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "countries")
public class Country {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    public Country(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
        address.setCountry(this);
    }
}
