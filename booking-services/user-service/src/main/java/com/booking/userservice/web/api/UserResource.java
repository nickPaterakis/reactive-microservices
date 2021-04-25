package com.booking.userservice.web.api;

import com.booking.bookingapi.core.user.UserEndpoint;
import com.booking.bookingapi.core.user.UserService;
import com.booking.bookingapi.core.user.dto.UserDetailsDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@Log4j2
//@Secured("ROLE_user")
public class UserResource implements UserEndpoint {

    private final UserService userService;

    @Autowired
    public UserResource(@Qualifier("UserServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @Override
    public Mono<UserDetailsDto> getUserDetails(UUID uuid) {
        return userService.getUserDetails(uuid);
    }

    @Override
    public Mono<UserDetailsDto> findUserByEmail(String email) {
        return userService.findUserByEmail(email);
    }

    @Override
    public UserDetailsDto saveUserDetails(UserDetailsDto userDetailsDto) {
        return null;
    }
    //
//    @Override
//    public UserDetailsDto saveUserDetails(UserDetailsDto userDetailsDto) {
//        return null;
//    }

    //    @PostMapping("/me")
//    public Mono<UserDetailsDto> saveUserDetails(@Valid @RequestBody UserDetailsRequest updateUserDetailsRequest, Mono<Principal> principal) {
//        // log.info(String.format("User.saveUserDetails(%s)", principal.getName()));
//        return userService.getUserDetails("76393fab-10b2-40bb-b3ef-b75a76829178")
//                .onErrorReturn(userService.saveUserDetails(updateUserDetailsRequest, principal).block());
//   }




//    @PostMapping("/me")
//    public UserDetailsDto saveUserDetails(@Valid @RequestBody UserDetailsRequest updateUserDetailsRequest, Principal principal) {
//        log.info(String.format("User.saveUserDetails(%s)", principal.getName()));
//        Optional<UserDetailsDto> userDetailsDtoOptional = userService.getUserDetails(principal.getName());
//        if(userDetailsDtoOptional.isEmpty()) {
//            System.out.println("inside if");
//            User user = new User(principal.getName());
//            user.setFirstName(updateUserDetailsRequest.getFirstName());
//            user.setLastName(updateUserDetailsRequest.getLastName());
//            user.setEmail(updateUserDetailsRequest.getEmail());
//            return userService.saveUserDetails(user);
//        }
//        //throw new UserDetailsNotFoundException(principal.getName());
//        return null;
//    }

//    @GetMapping("/properties/{id}")
//    public Set<Property> findByUser(@PathVariable Long id) {
//        Set<Property> properties = propertyClient.findByUser(id);
//        System.out.println(properties);
//        return properties;
//    }

//    @GetMapping("/{userName}")
//    public UserDetailsDto getUser(@PathVariable("userName") String userName) {
//        return userService.getUserDetails(userName);
//    }
//
//    @PostMapping("/{userName}/verify-password")
//    public VerifyPasswordResponse verifyUserPassword(@PathVariable("userName") String userName,
//                                                     @RequestBody String password) {
//        System.out.println("post method");
//        VerifyPasswordResponse returnValue = new VerifyPasswordResponse(false);
//
//        UserDetailsDto user = userService.getUserDetails(userName, password);
//
//        if (user != null) {
//            returnValue.setResult(true);
//        }
//
//        return returnValue;
//    }
}

