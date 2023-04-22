package com.booking.userservice.repository;

import com.booking.commondomain.dto.user.UserDetailsDto;
import com.booking.userservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public void updateProfileImage(UUID userId, String profileImagePath) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(userId));

        reactiveMongoTemplate.findOne(query, User.class)
                .doOnNext(user -> user.setProfileImage(profileImagePath))
                .flatMap(reactiveMongoTemplate::save).subscribe();
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
                .flatMap(reactiveMongoTemplate::save).subscribe();
    }

    @Override
    public Mono<User> findUserByEmail(String email) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return reactiveMongoTemplate.findOne(query, User.class);
    }
}
