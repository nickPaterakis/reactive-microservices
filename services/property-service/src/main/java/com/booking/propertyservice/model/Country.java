package com.booking.propertyservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "countries")
@SQLInsert(sql = "INSERT IGNORE INTO countries (name, id) " +
        "VALUES (?, ?)" )
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
