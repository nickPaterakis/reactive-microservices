package com.booking.userservice.service;

import com.booking.bookingapi.composite.dto.BookingUser;
import com.booking.bookingapi.core.user.UserService;
import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import com.booking.bookingutils.exception.NotFoundException;
import com.booking.userservice.dto.mapper.UserMapper;
import com.booking.userservice.model.User;
import com.booking.userservice.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static reactor.core.publisher.Mono.error;

@Service("UserServiceImpl")
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Mono<UserDetailsDto> getUserDetails(@AuthenticationPrincipal BookingUser user) {
        log.info("getUserDetails: {}", user.getId());
        return userRepository.findById(UUID.fromString(user.getId()))
                .switchIfEmpty(error(new NotFoundException("No user found for user id: " + user.getId())))
                .map(u -> modelMapper.map(u, UserDetailsDto.class));

    }

    @Override
    public Mono<UserDetailsDto> findUserByEmail(String email) {
        log.info("findUserByEmail: {}", email);
        return userRepository.findByEmail(email)
                .map(user-> modelMapper.map(user, UserDetailsDto.class));
    }

    @Override
    public Mono<UserDetailsDto> saveUserDetails(UserDetailsDto userDetailsDto) {
        log.info("saveUserDetails: {}", userDetailsDto.getId());
        User user = UserMapper.toUser(userDetailsDto);
        System.out.println(userDetailsDto);
        userRepository.save(user);
        return Mono.just(UserMapper.toUserDetailsDto(user));
    }
}
