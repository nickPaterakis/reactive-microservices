package com.booking.userservice.web.api;

import com.booking.bookingapi.user.UserService;
import com.booking.bookingapi.user.dto.BookingUser;
import com.booking.bookingapi.user.dto.UserDetailsDto;
import com.booking.bookingapi.user.dto.UserDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.util.UUID;

@CrossOrigin("*")
@RequestMapping("/users")
@RestController
@Log4j2
public class UserResource {

    private final UserService userService;
    private final Path basePath = Path.of("C:\\Users\\Nicholas\\Documents\\Projects\\Spring\\booking\\booking-services\\property-service\\src\\main\\resources\\images");

    @Autowired
    public UserResource(@Qualifier("UserServiceImpl") UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/me")
    public Mono<UserDetailsDto> getUserDetails(@AuthenticationPrincipal BookingUser user) {
        return userService.getUserDetails(user);
    }

    @GetMapping("user-id/{userId}")
    public Mono<UserDto> getUserById(@PathVariable UUID userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/{email}")
    public Mono<UserDetailsDto> findUserByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email);
    }

    @PutMapping("/update")
    public Mono<Void> updateUser(@RequestBody UserDetailsDto userDetailsDto) {
        return userService.updateUser(userDetailsDto);
    }

    @PostMapping(value = "/imageUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadImage(
            @RequestPart("userId") String userId,
            @RequestPart("file") Mono<FilePart> filePartMono) {

        filePartMono
                .doOnNext(fp -> userService.uploadImage(userId, "http://127.0.0.1:8887" + "/" + fp.filename()))
                .flatMap(fp -> fp.transferTo(basePath.resolve(fp.filename())))
                .then().subscribe();
        return null;
    }
}


