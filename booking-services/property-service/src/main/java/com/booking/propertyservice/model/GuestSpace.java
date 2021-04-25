package com.booking.propertyservice.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "guest_spaces")
public class GuestSpace extends NameEntity {
}
