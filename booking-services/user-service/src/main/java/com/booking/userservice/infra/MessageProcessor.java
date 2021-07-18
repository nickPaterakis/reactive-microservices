package com.booking.userservice.infra;

import com.booking.bookingapi.event.Event;
import com.booking.bookingapi.user.UserService;
import com.booking.bookingapi.user.dto.UserDetailsDto;
import com.booking.bookingapi.validationgroup.CreateDefaultReservation;
import com.booking.bookingutils.exception.EventProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@EnableBinding(Sink.class)
@Log4j2
public class MessageProcessor {

    private final UserService userService;
    private final Validator validator;

    @Autowired
    public MessageProcessor(@Qualifier("UserServiceImpl") UserService userService,
                            Validator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @StreamListener(target = Sink.INPUT)
    public void process(Event<Integer, UserDetailsDto> event) {
        log.info("Process message created at {}...", event.getEventCreatedAt());
        if (event.getEventType() == Event.Type.CREATE) {
            UserDetailsDto userDetailsDto = event.getData();

            Set<ConstraintViolation<UserDetailsDto>> violations = validator.validate(userDetailsDto, CreateDefaultReservation.class);

            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }

            userService.saveUserDetails(userDetailsDto);
        } else {
            String errorMessage =
                    "Incorrect event type: "
                            .concat(event.getEventType().toString())
                            .concat(", expected a CREATE or DELETE event.");
            log.warn(errorMessage);
            throw new EventProcessingException(errorMessage);
        }
    }
}
