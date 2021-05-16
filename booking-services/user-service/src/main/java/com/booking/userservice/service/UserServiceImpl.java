package com.booking.userservice.service;

import com.booking.bookingapi.core.user.UserService;
import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import com.booking.bookingutils.exception.NotFoundException;
import com.booking.userservice.dto.mapper.UserMapper;
import com.booking.userservice.model.User;
import com.booking.userservice.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Mono<UserDetailsDto> getUserDetails(UUID uuid) {
        log.info("getUserDetails: {}", uuid.toString());
        return userRepository.findById(uuid)
                .switchIfEmpty(error(new NotFoundException("No user found for user id: " + uuid.toString())))
                .map(user -> modelMapper.map(user, UserDetailsDto.class));

    }

    @Override
    public Mono<UserDetailsDto> findUserByEmail(String email) {
        log.info("findUserByEmail: {}", email);
        return userRepository.findByEmail(email)
                .map(user-> modelMapper.map(user, UserDetailsDto.class));
    }

    @Override
    public UserDetailsDto saveUserDetails(UserDetailsDto userDetailsDto) {
        log.info("saveUserDetails: {}", userDetailsDto.getId());
        User user = UserMapper.toUser(userDetailsDto);
        userRepository.save(user).block();;
        return UserMapper.toUserDetailsDto(user);
    }
}
