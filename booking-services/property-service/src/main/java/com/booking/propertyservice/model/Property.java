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
@Table(name = "properties")
public class Property extends BaseEntity{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "property_type_id")
    private PropertyType propertyType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "guest_space_id")
    private GuestSpace guestSpace;

    @Column(name = "max_guest_number")
    private Integer maxGuestNumber;

    @Column(name = "bedroom_number")
    private Integer bedroomNumber;

    @Column(name = "bath_number")
    private  Integer bathNumber;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price_per_night")
    private Float pricePerNight;

    @Column(name = "owner")
    private String owner;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch=FetchType.EAGER)
    @JoinTable(
            name = "property_amenities",
            joinColumns = @JoinColumn(name = "property_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    Set<Amenity> amenities;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private Set<Reservation> reservations;

    @OneToMany(mappedBy = "property", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Image> images;

}
