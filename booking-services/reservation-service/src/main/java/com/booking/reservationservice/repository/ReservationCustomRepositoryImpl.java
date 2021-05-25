package com.booking.reservationservice.repository;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationCustomRepositoryImpl implements ReservationCustomRepository {

    ReactiveMongoTemplate reactiveMongoTemplate;

//    @Autowired
//    public ReservationCustomRepositoryImpl(MongoTemplate mongoTemplate) {
//        this.mongoTemplate = mongoTemplate;
//    }

    @Override
    public Flux<Long> findPropertyIds(String location, LocalDate checkIn, LocalDate checkOut) {
        final Query query = new Query();

        final List<Criteria> criteria = new ArrayList<>();

        if (location != null && !location.isEmpty())
            criteria.add(Criteria.where("location").is(location));

        criteria.add(Criteria.where("checkIn").gte(checkIn).and("checkIn").gte(checkOut)
                .orOperator(Criteria.where("checkOut").lt(checkIn).and("checkOut").lt(checkOut)));

        query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        query.fields().include("propertyId");
        return reactiveMongoTemplate.find(query, Long.class);
    }
}
