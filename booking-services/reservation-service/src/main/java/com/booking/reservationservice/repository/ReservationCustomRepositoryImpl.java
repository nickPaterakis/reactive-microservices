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

        final List<Criteria> criterias = new ArrayList<>();
        final Criteria criteria = new Criteria();
        System.out.println(location);
        if (location != null && !location.isEmpty())
            criterias.add(Criteria.where("location").is(location));

//        criterias.add(criteria.andOperator(
//                Criteria.where("checkIn").gte(checkIn),
//                Criteria.where("checkIn").gte(checkOut)
//        ).orOperator(
//                Criteria.where("checkOut").lt(checkIn),
//                Criteria.where("checkOut").lt(checkOut)
//        ));
                //.orOperator(Criteria.where("checkOut").lt(checkIn).and("checkOut").lt(checkOut)));

        query.addCriteria(new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()])));
        query.fields().include("propertyId");
        return reactiveMongoTemplate.find(query, Long.class);
    }
}
