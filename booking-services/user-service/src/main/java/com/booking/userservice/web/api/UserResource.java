package com.booking.userservice.web.api;

import com.booking.bookingapi.user.UserService;
import com.booking.bookingapi.user.dto.BookingUser;
import com.booking.bookingapi.user.dto.UserDetailsDto;
import com.booking.bookingapi.user.dto.UserDto;
import com.booking.bookingapi.validationgroup.UpdateUser;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@CrossOrigin("*")
@RequestMapping("/users")
@RestController
@Log4j2
@Validated
public class UserResource {

    private final UserService userService;
    private final Storage storage;

    @Autowired
    public UserResource(@Qualifier("UserServiceImpl") UserService userService, Storage storage) {
        this.userService = userService;
        this.storage = storage;
    }
    
    @GetMapping("/me")
    public Mono<UserDetailsDto> getUserDetails(@Valid @AuthenticationPrincipal BookingUser user) {
        return userService.getUserDetails(user);
    }

    @GetMapping("user-id/{userId}")
    public Mono<UserDto> getUserById(@NotNull @PathVariable UUID userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/{email}")
    public Mono<UserDetailsDto> findUserByEmail(@Email @PathVariable String email) {
        return userService.findUserByEmail(email);
    }

    @PreAuthorize("hasRole('BOOKING_USER')")
    @PutMapping("/update")
    @Validated(UpdateUser.class)
    public Mono<Void> updateUser(@Valid @RequestBody UserDetailsDto userDetailsDto) {
        return userService.updateUser(userDetailsDto);
    }

    @PreAuthorize("hasRole('BOOKING_USER')")
    @PostMapping(value = "/imageUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<Void> uploadImage(
            @NotEmpty @RequestPart("userId") String userId,
            @NotNull @RequestPart("file") Mono<FilePart> filePartMono) {
        String imagesURL = "images/users/" + userId;
        filePartMono.doOnNext(fp -> userService.uploadImage(userId, imagesURL + "/" + fp.filename())).zipWith(
                filePartMono.flatMap(filePart -> filePart.content().shareNext()),
                (a, b) -> {
                    final BlobId blobId = BlobId.of("booking-uniwa",  imagesURL + "/" + a.filename());
                    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
                    byte[] bytes = b.asByteBuffer().array();
                    storage.create(blobInfo, bytes);
                    return Mono.empty();
                }).then().subscribe();
        return Mono.empty();
    }
}


