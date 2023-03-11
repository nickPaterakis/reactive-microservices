package com.booking.userservice.repository;

import com.booking.commondomain.dto.user.UserDetailsDto;
import com.booking.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class UserCustomRepositoryImpl implements UserCustomRepository {

    ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    public UserCustomRepositoryImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public void updateProfileImage(UUID userId, String profileImagePath) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(userId));

        reactiveMongoTemplate.findOne(query, User.class)
                .doOnNext(user -> {
                    user.setProfileImage(profileImagePath);
                    System.out.println(user);

                }).flatMap(user -> reactiveMongoTemplate.save(user)).subscribe();
    }

    @Override
    public void updateUser(UserDetailsDto userDetailsDto) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(UUID.fromString(userDetailsDto.getId())));

        reactiveMongoTemplate.findOne(query, User.class)
                .doOnNext(user -> user
                            .setFirstName(userDetailsDto.getFirstName())
                            .setLastName(userDetailsDto.getLastName())
                            .setPhone(userDetailsDto.getPhone()))
                .flatMap(user -> reactiveMongoTemplate.save(user)).subscribe();
    }

    @Override
    public Mono<User> findUserByEmail(String email) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return reactiveMongoTemplate.findOne(query, User.class);
    }
}
