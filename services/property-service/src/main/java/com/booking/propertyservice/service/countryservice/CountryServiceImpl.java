package com.booking.propertyservice.service.countryservice;

import com.booking.commondomain.dto.property.CountryDto;
import com.booking.propertyservice.repository.CountryRepository;
import com.booking.propertyservice.utils.ReactiveUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;
    private final ReactiveUtils reactiveUtils;

    @Override
    public Flux<CountryDto> getAllCountries() {
        log.info("Retrieving all countries");
        return reactiveUtils.asyncFlux(() -> Flux.fromIterable(
                        countryRepository.findAll().stream()
                                .map(country -> modelMapper.map(country, CountryDto.class))
                                .collect(Collectors.toList())));
    }
}
