package com.booking.propertyservice.service;

import com.booking.bookingapi.property.CountryService;
import com.booking.bookingapi.property.Dto.CountryDto;
import com.booking.propertyservice.repository.CountryRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;

import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service("CountryServiceImpl")
@Log4j2
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;
    private final Scheduler scheduler;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, ModelMapper modelMapper, Scheduler scheduler) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
        this.scheduler = scheduler;
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<CountryDto> getCountries(String name) {
        log.info("GetCountries: {}", name);
        return asyncFlux(() -> Flux.fromIterable(
                countryRepository.findCountryByName(name).stream()
                .map(country -> modelMapper.map(country, CountryDto.class))
                .collect(Collectors.toList())));
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<CountryDto> getCountries() {
        log.info("GetCountries");
        return asyncFlux(() -> Flux.fromIterable(
                StreamSupport.stream(countryRepository.findAll().spliterator(), false)
                        .map(country -> modelMapper.map(country, CountryDto.class))
                        .collect(Collectors.toList())));
    }

    private <T> Flux<T> asyncFlux(Supplier<Publisher<T>> publisherSupplier) {
        return Flux.defer(publisherSupplier).subscribeOn(scheduler);
    }
}
