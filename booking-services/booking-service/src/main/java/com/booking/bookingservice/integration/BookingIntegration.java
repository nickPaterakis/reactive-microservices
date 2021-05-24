package com.booking.bookingservice.integration;

import com.booking.bookingapi.core.property.CountryService;
import com.booking.bookingapi.core.property.Dto.CountryDto;
import com.booking.bookingapi.core.property.Dto.PageProperties;
import com.booking.bookingapi.core.property.Dto.PropertyDetailsDto;
import com.booking.bookingapi.core.property.PropertyService;
import com.booking.bookingapi.core.user.UserService;
import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import com.booking.bookingapi.event.Event;
import com.booking.bookingutils.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.booking.bookingapi.event.Event.Type.CREATE;
import static com.booking.bookingapi.event.Event.Type.DELETE;
import static org.springframework.integration.support.MessageBuilder.withPayload;

@EnableBinding(MessageSources.class)
@Component
@Log4j2
public class BookingIntegration implements CountryService, PropertyService, UserService {

    private final WebClient.Builder webClientBuilder;
    private final MessageSources messageSources;
    private final ObjectMapper mapper;
    private final String propertyServiceUrl;
    private final String userServiceUrl;
    private WebClient webClient;

    @Autowired
    public BookingIntegration(
            WebClient.Builder webClientBuilder,
            ObjectMapper mapper,
            MessageSources messageSources,
            @Value("${app.property-service.host}") String productServiceHost,
            @Value("${app.user-service.host}") String userServiceHost) {

        this.webClientBuilder = webClientBuilder;
        this.mapper = mapper;
        this.messageSources = messageSources;

        var http = "http://";

        propertyServiceUrl = http.concat(productServiceHost);
        userServiceUrl = http.concat(userServiceHost);
    }

    @Override
    public Flux<CountryDto> getCountries(String name) {

//        var url = "http://localhost:8081"
//                .concat("/countries/")
//                .concat(name);
        var url = propertyServiceUrl
                .concat("/countries/")
                .concat(name);

        return getWebClient()
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(CountryDto.class);
    }

//    @Override
//    public Mono<PageProperties> searchProperties(String location, LocalDate checkIn, LocalDate checkOut, int guestNumber, int currentPage) {
//
//        var url = UriComponentsBuilder
//                // .fromUriString( "http://localhost:8081"
//                .fromUriString(propertyServiceUrl
//                .concat("/properties/search")
//                .concat("?location={location}&checkIn={checkIn}&checkOut={checkOut}&guestNumber={guestNumber}&currentPage={currentPage}"))
//                .build(location, checkIn, checkOut, guestNumber, currentPage);
//        System.out.println(url);
//
//        return getWebClient()
//                .get()
//                .uri(url)
//                .retrieve()
//                .bodyToMono(PageProperties.class);
//    }

    @Override
    public Mono<PageProperties> getProperties(UUID ownerId) {
        var url = UriComponentsBuilder
//                .fromUriString("http://localhost:8081"
                .fromUriString(propertyServiceUrl
                        .concat("/properties/{ownerId}"))
                .build(ownerId.toString());

        return getWebClient()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(PageProperties.class)
                .onErrorMap(WebClientResponseException.class, ex -> new NotFoundException(ex.getMessage()));
    }

    @Override
    public Mono<PropertyDetailsDto> getProperty(Long propertyId) {
        var url = UriComponentsBuilder
//                .fromUriString("http://localhost:8081"
                .fromUriString(propertyServiceUrl
                        .concat("/properties/property/{propertyId}"))
                .build(propertyId);

        return getWebClient()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(PropertyDetailsDto.class)
                .onErrorMap(WebClientResponseException.class, ex -> new NotFoundException(ex.getMessage()));
    }

    @Override
    public Mono<Void> createProperty(PropertyDetailsDto propertyDetailsDto) {
        System.out.println(propertyDetailsDto);
        messageSources
                .outputProperties()
                .send(withPayload(new Event<>(CREATE, 1, propertyDetailsDto)).build());
        return Mono.empty();
    }

    @Override
    public void deleteProperty(Long id) {
        messageSources
                .outputProperties()
                .send(withPayload(new Event<>(DELETE, id, null)).build());
    }

    @Override
    public Mono<UserDetailsDto> getUserDetails(UUID uuid) {
        var url = UriComponentsBuilder
//                .fromUriString("http://localhost:8082"
                .fromUriString(userServiceUrl
                        .concat("/users/me/{uuid}"))
                .build(uuid.toString());
        System.out.println(uuid.toString());
        return getWebClient()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(UserDetailsDto.class)
                .onErrorMap(WebClientResponseException.class, ex -> new NotFoundException(ex.getMessage()));
    }

    @Override
    public Mono<UserDetailsDto> findUserByEmail(String email) {
        var url = UriComponentsBuilder
//                .fromUriString("http://localhost:8082"
                .fromUriString(userServiceUrl
                .concat("/users/{email}"))
                .build(email);

        //.onErrorMap(WebClientResponseException.class, ex -> new NotFoundException(ex.getMessage()));

        return getWebClient()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(UserDetailsDto.class)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public UserDetailsDto saveUserDetails(UserDetailsDto body) {
        messageSources
                .outputUsers()
                .send(withPayload(new Event<>(CREATE,1, body)).build());
        return body;
    }

    private WebClient getWebClient() {
        if (webClient == null) {
            webClient = webClientBuilder.build();
        }
        return webClient;
    }
//
//    private Throwable handleException(Throwable ex) {
//        if (!(ex.getClass().equals(WebClientResponseException.class))) {
//            log.warn("Got a unexpected error: {}, will rethrow it", ex.toString());
//            return ex;
//        }
//
//        WebClientResponseException wcre = new WebClientResponseException();
//
//        if (wcre.getStatusCode() == NOT_FOUND) {
//            return new NotFoundException(ex.getMessage());
//        } else {
//            log.warn("Got a unexpected HTTP error: {}, will rethrow it", wcre.getStatusCode());
//            log.warn("Error body: {}", wcre.getResponseBodyAsString());
//            throw wcre;
//        }



//        return switch (wcre.getStatusCode()) {
//            case NOT_FOUND:
//                new NotFoundException(getErrorMessage(wcre));
//            //case UNPROCESSABLE_ENTITY -> new InvalidInputException(getErrorMessage(wcre));
//            default: {
//                log.warn("Got a unexpected HTTP error: {}, will rethrow it", wcre.getStatusCode());
//                log.warn("Error body: {}", wcre.getResponseBodyAsString());
//                throw wcre;
//            }
////        };
//    }
//
//    private String getErrorMessage(WebClientResponseException ex) {
//        try {
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>:"+ mapper.readValue(ex.getResponseBodyAsString(), ErrorResponse.class).getMessage());
//            return mapper.readValue(ex.getResponseBodyAsString(), ErrorResponse.class).getMessage();
//        } catch (IOException ioException) {
//            return ex.getMessage();
//        }
//    }

}
