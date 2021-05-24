//package com.booking.propertyservice.infra;
//
//import com.booking.bookingapi.core.property.Dto.PropertyDetailsDto;
//import com.booking.bookingapi.core.property.PropertyService;
//import com.booking.bookingapi.event.Event;
//import com.booking.bookingutils.exception.EventProcessingException;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.cloud.stream.messaging.Sink;
//
//@EnableBinding(Sink.class)
//@Log4j2
//public class MessageProcessor {
//
//    private final PropertyService propertyService;
//
//    @Autowired
//    public MessageProcessor(@Qualifier("PropertyServiceImpl") PropertyService propertyService) {
//        this.propertyService = propertyService;
//    }
//
//    @StreamListener(target = Sink.INPUT)
//    public void process(Event<Long, PropertyDetailsDto> event) {
//        log.info("Process message created at {}...", event.getEventCreatedAt());
//        switch (event.getEventType()) {
//            case CREATE: {
//                PropertyDetailsDto propertyDetailsDto = event.getData();
//                log.info("Create property with ID: {}", propertyDetailsDto.getTitle());
//                propertyService.createProperty(propertyDetailsDto);
//                break;
//            }
//            case DELETE: {
//                Long id = event.getKey();
//                log.info("Delete property with ID: {}", id);
//                propertyService.deleteProperty(id);
//                break;
//            }
//            case UPDATE: {
//
//            }
//            default: {
//                String errorMessage =
//                        "Incorrect event type: "
//                                .concat(event.getEventType().toString())
//                                .concat(", expected a CREATE, UPDATE or DELETE event.");
//                log.warn(errorMessage);
//                throw new EventProcessingException(errorMessage);
//            }
//        }
//    }
//}
