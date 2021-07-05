package com.booking.reservationservice;

import com.booking.reservationservice.model.Reservation;
import com.booking.reservationservice.repository.ReservationRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
@Log4j2
public class DataInitializer implements CommandLineRunner {

    private final ReservationRepository reservationRepository;
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    public DataInitializer(ReservationRepository reservationRepository, ReactiveMongoTemplate reactiveMongoTemplate ) {
        this.reservationRepository = reservationRepository;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public void run(String... args) {
        createReservations();
    }

    private void createReservations() {
        log.info("Creating Reservations");

//        reactiveMongoTemplate.save(new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,5,1),
//                        LocalDate.of( 2021, 5, 10),
//                        1L,
//                        "Greece",
//                        UUID.fromString("18f31b63-8db7-4b5e-bf32-783bae54376a")
//                ))
//                .log()
//                .then(reservationRepository.count())
//                .subscribe(c -> log.info("{} reservations created", c));
           reservationRepository.deleteAll().
                log()
                .then(reservationRepository.count())
                .subscribe(c -> log.info("{} reservations created", c));
        reservationRepository.saveAll(
            Flux.just(
                new Reservation(
                        UUID.randomUUID(),
                        LocalDate.of(2021,5,1),
                        LocalDate.of( 2021, 5, 10),
                        1L,
                        "Greece",
                        BigDecimal.valueOf(270),
                        UUID.fromString("18f31b63-8db7-4b5e-bf32-783bae54376a"),
                        UUID.fromString("79a4375e-386a-4352-8667-3c6007a6e6a4")
                ),
                new Reservation(
                        UUID.randomUUID(),
                        LocalDate.of(2021,5,1),
                        LocalDate.of( 2021, 5, 10),
                        2L,
                        "Germany",
                        BigDecimal.valueOf(270),
                        UUID.fromString("18f31b63-8db7-4b5e-bf32-783bae54376a"),
                        UUID.fromString("79a4375e-386a-4352-8667-3c6007a6e6a4")
                ),
                new Reservation(
                        UUID.randomUUID(),
                        LocalDate.of(2021,5,1),
                        LocalDate.of( 2021, 5, 10),
                        3L,
                        "Hungary",
                        BigDecimal.valueOf(270),
                        UUID.fromString("18f31b63-8db7-4b5e-bf32-783bae54376a"),
                        UUID.fromString("79a4375e-386a-4352-8667-3c6007a6e6a4")
                ),
                new Reservation(
                        UUID.randomUUID(),
                        LocalDate.of(2021,5,1),
                        LocalDate.of( 2021, 5, 10),
                        4L,
                        "Spain",
                        BigDecimal.valueOf(270),
                        UUID.fromString("18f31b63-8db7-4b5e-bf32-783bae54376a"),
                        UUID.fromString("79a4375e-386a-4352-8667-3c6007a6e6a4")
                )
//                new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,5,1),
//                        LocalDate.of( 2021, 5, 10),
//                        5L,
//                        "Germany",
//                        BigDecimal.valueOf(270),
//                        UUID.fromString("18f31b63-8db7-4b5e-bf32-783bae54376a"),
//                        UUID.fromString("79a4375e-386a-4352-8667-3c6007a6e6a4")
//                ),
//                new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,5,1),
//                        LocalDate.of( 2021, 5, 10),
//                        6L,
//                        "Greece",
//                        BigDecimal.valueOf(270),
//                        UUID.fromString("18f31b63-8db7-4b5e-bf32-783bae54376a"),
//                        UUID.fromString("79a4375e-386a-4352-8667-3c6007a6e6a4")
//                ),
//                new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,5,1),
//                        LocalDate.of( 2021, 5, 10),
//                        7L,
//                        "Greece",
//                        BigDecimal.valueOf(270),
//                        UUID.fromString("18f31b63-8db7-4b5e-bf32-783bae54376a"),
//                        UUID.fromString("79a4375e-386a-4352-8667-3c6007a6e6a4")
//                ),
//                new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,5,20),
//                        LocalDate.of( 2021, 5, 30),
//                        8L,
//                        "Greece",
//                        BigDecimal.valueOf(270),
//                        UUID.fromString("18f31b63-8db7-4b5e-bf32-783bae54376a"),
//                        UUID.fromString("79a4375e-386a-4352-8667-3c6007a6e6a4")
//                ),
//                new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,5,20),
//                        LocalDate.of( 2021, 5, 30),
//                        9L,
//                        "Greece",
//                        BigDecimal.valueOf(270),
//                        UUID.fromString("18f31b63-8db7-4b5e-bf32-783bae54376a"),
//                        UUID.fromString("79a4375e-386a-4352-8667-3c6007a6e6a4")
//                ),
//                new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,6,1),
//                        LocalDate.of( 2021, 6, 10),
//                        10L,
//                        "Greece",
//                        BigDecimal.valueOf(270),
//                        UUID.fromString("18f31b63-8db7-4b5e-bf32-783bae54376a"),
//                        UUID.fromString("79a4375e-386a-4352-8667-3c6007a6e6a4")
//                ),
//                new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,6,1),
//                        LocalDate.of( 2021, 6, 10),
//                        11L,
//                        "Greece",
//                        BigDecimal.valueOf(270),
//                        UUID.fromString("18f31b63-8db7-4b5e-bf32-783bae54376a"),
//                        UUID.fromString("18f31b63-8db7-4b5e-bf32-783bae54376a")
//                ),
//                new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,5,1),
//                        LocalDate.of( 2021, 5, 10),
//                        12L,
//                        "Greece",
//                        BigDecimal.valueOf(270),
//                        UUID.fromString("18f31b63-8db7-4b5e-bf32-783bae54376a"),
//                        UUID.fromString("18f31b63-8db7-4b5e-bf32-783bae54376a")
//                ),
//                new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,5,10),
//                        LocalDate.of( 2021, 5, 29),
//                        13L,
//                        "Greece",
//                        BigDecimal.valueOf(270),
//                        UUID.fromString("18f31b63-8db7-4b5e-bf32-783bae54376a"),
//                        UUID.fromString("18f31b63-8db7-4b5e-bf32-783bae54376a")
//                ),
//                new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,5,10),
//                        LocalDate.of( 2021, 5, 29),
//                        14L,
//                        "Greece",
//                        BigDecimal.valueOf(270),
//                        UUID.fromString("18f31b63-8db7-4b5e-bf32-783bae54376a"),
//                        UUID.fromString("18f31b63-8db7-4b5e-bf32-783bae54376a")
//                )
            )
        )
        .log()
        .then(reservationRepository.count())
        .subscribe(c -> log.info("{} reservations created", c));

    }

    public void findAllReserv() {
        Flux<Reservation> reservationFlux = reactiveMongoTemplate.findAll(Reservation.class);
        List<Reservation> reservationList = reservationFlux.collectList().block();
        reservationList.forEach(r -> System.out.println(r));
    }
}
