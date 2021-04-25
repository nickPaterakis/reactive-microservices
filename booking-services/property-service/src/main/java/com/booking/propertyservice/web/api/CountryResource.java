package com.booking.propertyservice.web.api;

import com.booking.bookingapi.core.property.CountryEndpoint;
import com.booking.bookingapi.core.property.Dto.CountryDto;
import com.booking.bookingapi.core.property.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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