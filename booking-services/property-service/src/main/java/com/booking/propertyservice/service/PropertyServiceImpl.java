package com.booking.propertyservice.service;

import com.booking.bookingapi.core.property.PropertyService;
import com.booking.propertyservice.dto.mapper.PropertyMapper;
import com.booking.bookingapi.core.property.Dto.PropertyDto;
import com.booking.propertyservice.repository.PropertyRepository;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;


import java.time.LocalDate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service("PropertyServiceImpl")
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final Scheduler scheduler;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository, Scheduler scheduler) {
        this.propertyRepository = propertyRepository;
        this.scheduler = scheduler;
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PropertyDto> searchProperties(
            String location,
            LocalDate checkIn,
            LocalDate checkOut,
            int guestNumber,
            int currentPage
    ) {
        Pageable pageable;
        pageable = PageRequest.of(currentPage,5);
        System.out.println(currentPage);
        return asyncFlux(() -> Flux.fromIterable(
                propertyRepository.searchProperties(location, checkIn, checkOut, guestNumber, pageable)
                        .stream()
                        .map(PropertyMapper::toPropertyDto)
                        .collect(Collectors.toList())));
    }

    @Override
    public Flux<PropertyDto> getProperties(String ownerId) {
        return asyncFlux(()-> Flux.fromIterable(
                propertyRepository.findAllByOwner_id(ownerId)
                        .stream()
                        .map(PropertyMapper::toPropertyDto)
                        .collect(Collectors.toList())));
    }

    private <T> Flux<T> asyncFlux(Supplier<Publisher<T>> publisherSupplier) {
        return Flux.defer(publisherSupplier).subscribeOn(scheduler);
    }

//    @Override
//    public Set<PropertyDto> findByUser(Long id) {
//        System.out.println(id);
//        Set<Property> properties = propertyRepository.findByUser(id);
//
//        if (!properties.isEmpty()) {
//            return properties
//                    .stream()
//                    .map(PropertyMapper::toPropertyDto)
//                    .collect(Collectors.toSet());
//        }
//        throw new EntityNotFoundException(Property.class);
//    }
}
