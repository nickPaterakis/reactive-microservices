package com.booking.propertyservice.web.api;

import com.booking.bookingapi.property.CountryEndpoint;
import com.booking.bookingapi.property.CountryService;
import com.booking.bookingapi.property.Dto.CountryDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@Log4j2
public class CountryResource implements CountryEndpoint {


    private final CountryService countryService;

    @Autowired
    public CountryResource(@Qualifier("CountryServiceImpl") CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public Flux<CountryDto> getCountries(String name) {
        log.info(String.format("Country.findCountryByName(%s)", name));
        return countryService.getCountries(name);
    }

}