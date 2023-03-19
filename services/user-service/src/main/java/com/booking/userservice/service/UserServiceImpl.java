package com.booking.userservice.service;

import com.booking.commondomain.dto.user.BookingUser;
import com.booking.commondomain.dto.user.UserDetailsDto;
import com.booking.commondomain.dto.user.UserDto;
import com.booking.bookingutils.exception.NotFoundException;
import com.booking.userservice.mapper.UserMapper;
import com.booking.userservice.model.User;
import com.booking.userservice.repository.UserRepository;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static reactor.core.publisher.Mono.error;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Storage storage;

    @Override
    public Mono<UserDetailsDto> getUserDetails(@AuthenticationPrincipal BookingUser user) {
        log.info("getUserDetails: {}", user.getId());
        return userRepository.findById(UUID.fromString(user.getId()))
                .switchIfEmpty(error(new NotFoundException("No user found for user id: " + user.getId())))
                .map(u -> modelMapper.map(u, UserDetailsDto.class));
    }

    @Override
    public Mono<UserDto> getUserById(UUID userId) {
        log.info("getUserById: {}", userId);
        return userRepository.findById(userId)
                .switchIfEmpty(error(new NotFoundException("No user found for user id: " + userId.toString())))
                .map(UserMapper::toUserDto);
    }

    @Override
    public Mono<UserDetailsDto> findUserByEmail(String email) {
        log.info("findUserByEmail: {}", email);
        return userRepository.findUserByEmail(email)
                .switchIfEmpty(Mono.empty())
                .map(UserMapper::toUserDetailsDto);
    }

    @Override
    public Mono<UserDetailsDto> saveUserDetails(UserDetailsDto userDetailsDto) {
        log.info("Create user with ID: {}", userDetailsDto.getId());
        User user = UserMapper.toUser(userDetailsDto);
        return userRepository.save(user)
                .map(UserMapper::toUserDetailsDto);
    }

    @Override
    public Mono<Void> updateUser(UserDetailsDto userDetailsDto) {
        log.info("Update User: {}", userDetailsDto.getId());
        userRepository.updateUser(userDetailsDto);
        return Mono.empty();
    }

    @Override
    public Mono<Void> uploadImage(String userId, Mono<FilePart> filePartMono) {
        log.info("Update User Image: {}", userId);
        String imagesURL = "images/users/" + userId;
        filePartMono
                .doOnNext(fp -> userRepository.updateProfileImage(UUID.fromString(userId), imagesURL + "/" + fp.filename()))
                .zipWith(filePartMono.flatMap(filePart -> filePart.content().shareNext()),
                (a, b) -> {
                    final BlobId blobId = BlobId.of("booking-uniwa1",  imagesURL + "/" + a.filename());
                    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
                    byte[] bytes = b.asByteBuffer().array();
                    storage.create(blobInfo, bytes);
                    return Mono.empty();
                })
                .then()
                .subscribe();
        return Mono.empty();
    }
}
