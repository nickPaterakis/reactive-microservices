package com.booking.propertyservice.service;

import com.booking.commondomain.dto.property.CountryDto;
import reactor.core.publisher.Flux;

public interface CountryService {

    Flux<CountryDto> getCountries();
}
