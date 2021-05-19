package com.booking.propertyservice.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "images")
public class Image extends NameEntity {

    @ManyToOne( fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    @JoinColumn(name = "property_id")
    private Property property;

    public Image(String name) {
        super(name);
    }
}
