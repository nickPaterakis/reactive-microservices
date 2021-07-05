package com.booking.propertyservice.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class NameEntity extends BaseEntity {
    @Column(name = "name")
    protected String name;
}
