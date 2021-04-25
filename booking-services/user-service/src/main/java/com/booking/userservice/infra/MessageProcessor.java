package com.booking.userservice.infra;

import com.booking.bookingapi.core.user.UserService;
import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import com.booking.bookingapi.event.Event;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import static com.booking.bookingapi.event.Event.Type.CREATE;

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

        if (event.getEventType().equals(CREATE)) {
            UserDetailsDto userDetailsDto = event.getData();
            //log.info("Create user with ID: {}", userDetailsDto.getUserId());
            userService.saveUserDetails(userDetailsDto);
        }
//        switch (event.getEventType()) {
//            case CREATE: {
//
//            }
////            case DELETE -> {
////                int productId = event.getKey();
////                log.info("Delete review with Product Id: {}", productId);
////                service.deleteReviews(productId);
////            }
//            default: {
//                String errorMessage =
//                        "Incorrect event type: "
//                                .concat(valueOf(event.getEventType()))
//                                .concat(", expected a CREATE or DELETE event");
//                log.warn(errorMessage);
//                throw new EventProcessingException(errorMessage);
//            }
//        }
//
//       // log.info("Message processing done!");
    }
}
