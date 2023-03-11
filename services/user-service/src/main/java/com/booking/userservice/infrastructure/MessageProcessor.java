package com.booking.userservice.infrastructure;

import com.booking.commondomain.dto.user.UserDetailsDto;
import com.booking.commondomain.event.Event;
import com.booking.userservice.service.UserService;
import com.booking.bookingutils.exception.EventProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@EnableBinding(Sink.class)
public class MessageProcessor {

    private final UserService userService;

    @StreamListener(target = Sink.INPUT)
    public void process(Event<Integer, UserDetailsDto> event) {
        log.info("Process message created at {}...", event.getEventCreatedAt());
        if (event.getEventType() == Event.Type.CREATE) {
            UserDetailsDto userDetailsDto = event.getData();

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
