package com.booking.bookingapi.property;

import com.booking.bookingapi.property.Dto.CountryDto;
import reactor.core.publisher.Flux;

public interface CountryService {

    Flux<CountryDto> getCountries(String name);

    Flux<CountryDto> getCountries();
}
