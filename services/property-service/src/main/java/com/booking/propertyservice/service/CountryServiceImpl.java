package com.booking.propertyservice.service;

import com.booking.commondomain.dto.property.CountryDto;
import com.booking.propertyservice.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;
    private final Scheduler scheduler;

    @Override
    public Flux<CountryDto> getCountries() {
        log.info("Get all countries");
        return asyncFlux(() -> Flux.fromIterable(
                countryRepository.findAll().stream()
                        .map(country -> modelMapper.map(country, CountryDto.class))
                        .collect(Collectors.toList())));
    }

    private <T> Flux<T> asyncFlux(Supplier<Publisher<T>> publisherSupplier) {
        return Flux.defer(publisherSupplier).subscribeOn(scheduler);
    }
}
