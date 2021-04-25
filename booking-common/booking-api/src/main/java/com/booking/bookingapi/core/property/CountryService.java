package com.booking.bookingapi.core.property;

import com.booking.bookingapi.core.property.Dto.CountryDto;
import reactor.core.publisher.Flux;

public interface CountryService {

    Flux<CountryDto> getCountries(String name);

}
