package com.booking.propertyservice.controller;

import com.booking.propertyservice.service.CountryService;
import com.booking.commondomain.dto.property.CountryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@CrossOrigin("*")
@Slf4j
@RequestMapping("/countries")
@RestController
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/all")
    public Flux<CountryDto> getCountries() {
        return countryService.getCountries();
    }

}