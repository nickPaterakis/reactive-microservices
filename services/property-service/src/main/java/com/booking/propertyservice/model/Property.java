package com.booking.propertyservice.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@Entity
@Table(name = "properties")
public class Property extends BaseEntity {

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
    private Integer bathNumber;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price_per_night")
    private Float pricePerNight;

    @Column(name = "owner")
    private String owner;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "property_amenities",
            joinColumns = @JoinColumn(name = "property_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private Set<Amenity> amenities = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(
            mappedBy = "property",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private List<Image> images  = new ArrayList<>();

    public void addAmenity(Amenity amenity) {
        amenities.add(amenity);
        amenity.getProperties().add(this);
    }

    public void addImage(Image image) {
        this.images.add(image);
        image.setProperty(this);
    }

}
