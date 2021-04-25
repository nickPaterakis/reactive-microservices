package com.booking.userservice.service;

import com.booking.bookingapi.core.user.UserService;
import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import com.booking.bookingutils.exception.NotFoundException;
import com.booking.userservice.dto.mapper.UserMapper;
import com.booking.userservice.model.User;
import com.booking.userservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Mono<UserDetailsDto> getUserDetails(UUID uuid) {
        return userRepository.findById(uuid)
                .map(user -> modelMapper.map(user, UserDetailsDto.class))
                .onErrorMap(ex -> new NotFoundException(uuid.toString()));
    }

    @Override
    public Mono<UserDetailsDto> findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user-> modelMapper.map(user, UserDetailsDto.class));
    }

    @Override
    public UserDetailsDto saveUserDetails(UserDetailsDto userDetailsDto) {
        User user = UserMapper.toUser(userDetailsDto);
        userRepository.save(user);
        return modelMapper.map(user, UserDetailsDto.class);
    }



    //    @Override
//    public Optional<UserDetailsDto> getUserDetails(String userId) {
//        Optional<User> user = Optional.ofNullable(userRepository.findByUserId(userId));
//        if (user.isPresent()) {
//            UserDetailsDto userDetailsDto = modelMapper.map(user, UserDetailsDto.class);
//            return Optional.ofNullable(userDetailsDto);
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public UserDetailsDto saveUserDetails(UserDetailsDto user) {
//        userRepository.save(modelMapper.map(user, User.class));
//        return user;
//    }

    //    @Override
//    public UserDetailsDto getUserDetails(String username, String password) {
//        UserDetailsDto returnValue = null;
//
//        User user = userRepository.findByEmail(username);
//
//        if (user == null) {
//            return returnValue;
//        }
//
//        if (bCryptPasswordEncoder.matches(password,
//                user.getPassword())) {
//            System.out.println("password matches");
//            returnValue = new UserDetailsDto();
//            BeanUtils.copyProperties(user, returnValue);
//
//        }
//        System.out.println("password doesn't matches");
//        return returnValue;
//    }
}
