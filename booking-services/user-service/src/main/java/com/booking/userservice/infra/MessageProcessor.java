package com.booking.userservice.infra;

import com.booking.bookingapi.user.UserService;
import com.booking.bookingapi.user.dto.UserDetailsDto;
import com.booking.bookingapi.event.Event;
import com.booking.bookingutils.exception.EventProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
@Log4j2
public class MessageProcessor {

    private final UserService userService;

    @Autowired
    public MessageProcessor(@Qualifier("UserServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @StreamListener(target = Sink.INPUT)
    public void process(Event<Integer, UserDetailsDto> event) {
        log.info("Process message created at {}...", event.getEventCreatedAt());
        switch (event.getEventType()) {
            case CREATE: {
                UserDetailsDto userDetailsDto = event.getData();
                log.info("Create user with ID: {}", userDetailsDto.getId());
                userService.saveUserDetails(userDetailsDto);
                break;
            }
            case DELETE: {

            }
            case UPDATE: {

            }
            default: {
                String errorMessage =
                        "Incorrect event type: "
                                .concat(event.getEventType().toString())
                                .concat(", expected a CREATE or DELETE event.");
                log.warn(errorMessage);
                throw new EventProcessingException(errorMessage);
            }
        }
    }
}
