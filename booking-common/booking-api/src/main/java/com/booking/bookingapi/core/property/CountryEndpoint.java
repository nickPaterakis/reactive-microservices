package com.booking.bookingapi.core.property;


import com.booking.bookingapi.core.property.Dto.CountryDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;

@RequestMapping("/countries")
public interface CountryEndpoint extends CountryService {

    @GetMapping("/{name}")
    @Override
    Flux<CountryDto> getCountries(@PathVariable String name);

}
