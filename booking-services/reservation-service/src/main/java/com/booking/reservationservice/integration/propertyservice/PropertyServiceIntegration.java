package com.booking.reservationservice.integration.propertyservice;

import com.booking.commondomain.dto.property.PropertyReservationDataDto;
import reactor.core.publisher.Mono;

public interface PropertyServiceIntegration {

    Mono<PropertyReservationDataDto> getPropertyById(Long propertyId);
}
