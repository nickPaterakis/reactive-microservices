package com.booking.userservice;

import com.booking.bookingapi.core.user.Role;
import com.booking.userservice.model.User;
import com.booking.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.UUID;


/** Store initial users and books in mongodb. */
@Component
public class DataInitializer implements CommandLineRunner {

    private static final UUID USER_NICHOLAS_IDENTIFIER =
            UUID.fromString("76393fab-10b2-40bb-b3ef-b75a76829178");


    private final UserRepository userRepository;

    @Autowired
    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        createUsers();
    }

    private void createUsers() {
        final Logger logger = LoggerFactory.getLogger(this.getClass());

        userRepository
                .save(
                        new User(
                                        USER_NICHOLAS_IDENTIFIER,
                                        "nikolas.paterakis@gmail.com",
                                        "Nicholas",
                                        "Paterakis",
                                        "68585858585",
                                        Collections.singletonList(Role.BOOKING_USER))
                )  .log()
                .then(userRepository.count())
                .subscribe(c -> logger.info("{} users created", c));;

    }
}

