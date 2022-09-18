package com.booking.propertyservice.web.api;

import com.booking.bookingapi.property.CountryService;
import com.booking.bookingapi.property.Dto.CountryDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@CrossOrigin("*")
@RequestMapping("/countries")
@RestController
@Log4j2
public class CountryResource {

    private final CountryService countryService;

    @Autowired
    public CountryResource(@Qualifier("CountryServiceImpl") CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/all")
    public Flux<CountryDto> getCountries() {
        log.info(String.format("Country.getCountries()"));
        return countryService.getCountries();
    }

}