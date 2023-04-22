package com.booking.userservice.controller;

import com.booking.userservice.service.UserService;
import com.booking.commondomain.dto.user.BookingUser;
import com.booking.commondomain.dto.user.UserDetailsDto;
import com.booking.commondomain.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
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
    public Mono<Void> updateUser(@Valid @RequestBody UserDetailsDto userDetailsDto) {
        return userService.updateUser(userDetailsDto);
    }

    @PreAuthorize("hasRole('BOOKING_USER')")
    @PostMapping(value = "/imageUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<Void> uploadImage(@NotEmpty @RequestPart("userId") String userId,
                                  @NotNull @RequestPart("file") Mono<FilePart> filePartMono) {
        return userService.uploadImage(userId, filePartMono);
    }
}


