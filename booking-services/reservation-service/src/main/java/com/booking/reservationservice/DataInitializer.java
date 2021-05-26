package com.booking.reservationservice;

import com.booking.reservationservice.repository.ReservationRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class DataInitializer implements CommandLineRunner {

    private final ReservationRepository reservationRepository;

    @Autowired
    public DataInitializer(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(String... args) {
        createReservations();
    }

    private void createReservations() {
        log.info("Creating Reservations");

        reservationRepository.deleteAll();
//        reservationRepository.saveAll(
//            Flux.just(
//                new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,5,1),
//                        LocalDate.of( 2021, 5, 10),
//                        1L,
//                        "Greece",
//                        UUID.fromString("6c443bc0-cfcf-4906-9cce-64cdf3bcfefb")
//                ),
//                new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,5,1),
//                        LocalDate.of( 2021, 5, 10),
//                        2L,
//                        "Germany",
//                        UUID.fromString("6c443bc0-cfcf-4906-9cce-64cdf3bcfefb")
//                ),
//                new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,5,1),
//                        LocalDate.of( 2021, 5, 10),
//                        3L,
//                        "Hungary",
//                        UUID.fromString("6c443bc0-cfcf-4906-9cce-64cdf3bcfefb")
//                ),
//                new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,5,1),
//                        LocalDate.of( 2021, 5, 10),
//                        4L,
//                        "Spain",
//                        UUID.fromString("6c443bc0-cfcf-4906-9cce-64cdf3bcfefb")
//                ),
//                new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,5,1),
//                        LocalDate.of( 2021, 5, 10),
//                        5L,
//                        "Germany",
//                        UUID.fromString("6c443bc0-cfcf-4906-9cce-64cdf3bcfefb")
//                ),
//                new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,5,1),
//                        LocalDate.of( 2021, 5, 10),
//                        6L,
//                        "Greece",
//                        UUID.fromString("6c443bc0-cfcf-4906-9cce-64cdf3bcfefb")
//                ),
//                new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,5,1),
//                        LocalDate.of( 2021, 5, 10),
//                        7L,
//                        "Greece",
//                        UUID.fromString("6c443bc0-cfcf-4906-9cce-64cdf3bcfefb")
//                ),
//                new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,5,20),
//                        LocalDate.of( 2021, 5, 30),
//                        8L,
//                        "Greece",
//                        UUID.fromString("6c443bc0-cfcf-4906-9cce-64cdf3bcfefb")
//                ),
//                new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,5,20),
//                        LocalDate.of( 2021, 5, 30),
//                        9L,
//                        "Greece",
//                        UUID.fromString("6c443bc0-cfcf-4906-9cce-64cdf3bcfefb")
//                ),
//                new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,6,1),
//                        LocalDate.of( 2021, 6, 10),
//                        10L,
//                        "Greece",
//                        UUID.fromString("6c443bc0-cfcf-4906-9cce-64cdf3bcfefb")
//                ),
//                new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,6,1),
//                        LocalDate.of( 2021, 6, 10),
//                        11L,
//                        "Greece",
//                        UUID.fromString("6c443bc0-cfcf-4906-9cce-64cdf3bcfefb")
//                ),
//                new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,5,1),
//                        LocalDate.of( 2021, 5, 10),
//                        12L,
//                        "Greece",
//                        UUID.fromString("6c443bc0-cfcf-4906-9cce-64cdf3bcfefb")
//                ),
//                new Reservation(
//                        UUID.randomUUID(),
//                        LocalDate.of(2021,5,10),
//                        LocalDate.of( 2021, 5, 15),
//                        13L,
//                        "Greece",
//                        UUID.fromString("6c443bc0-cfcf-4906-9cce-64cdf3bcfefb")
//                )
//            )
//        )
//        .log()
//        .then(reservationRepository.count())
//        .subscribe(c -> log.info("{} reservations created", c));
    }
}
