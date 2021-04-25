package com.booking.propertyservice.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class NameEntity extends BaseEntity {
    @Column(name = "name")
    private String name;
}
