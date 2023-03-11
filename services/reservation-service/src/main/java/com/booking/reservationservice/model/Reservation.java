package com.booking.reservationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "reservations")
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    private String id;

    private LocalDate checkIn;

    private LocalDate checkOut;

    private Long propertyId;

    private String location;

    private BigDecimal price;

    private UUID userId;

    private UUID ownerId;
}
