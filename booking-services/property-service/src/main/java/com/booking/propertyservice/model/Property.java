package com.booking.propertyservice.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "properties")
public class Property extends BaseEntity{

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "property_type_id")
    private PropertyType propertyType;

    @ManyToOne
    @JoinColumn(name = "guest_space_id")
    private GuestSpace guestSpace;

    @Column(name = "max_guest_number")
    private Integer maxGuestNumber;

    @Column(name = "bedroom_number")
    private Integer bedroomNumber;

    @Column(name = "bath_number")
    private  Integer bathNumber;

    @Column(name = "price_per_night")
    private Float pricePerNight;

    @Column( name = "owner_id")
    private String owner_id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "property_amenities",
            joinColumns = @JoinColumn(name = "property_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    Set<Amenity> amenities;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "property")
    private Set<Reservation> reservations;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "property")
    private Set<Image> images;

}
