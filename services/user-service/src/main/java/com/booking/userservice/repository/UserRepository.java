package com.booking.userservice.repository;

import com.booking.userservice.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserRepository extends ReactiveMongoRepository<User, UUID>,
UserCustomRepository {

    Mono<User> findById(UUID userId);
}
