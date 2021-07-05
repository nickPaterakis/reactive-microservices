package com.booking.reservationservice.config;

import com.mongodb.reactivestreams.client.MongoClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
@Log4j2
public class MongoConfig { // extends AbstractReactiveMongoConfiguration {

//    @Value("${spring.data.mongodb.host}")
//    private String mongoHost;
//
//    @Value("${spring.data.mongodb.port}")
//    private String mongoPort;
//
//    @Value("${spring.data.mongodb.database}")
//    private String mongoDB;

    @Autowired
    MongoClient mongoClient;

//    @Override
//    protected String getDatabaseName() {
//        return mongoDB;
//    }
//
//    @Override
//    public MongoClient reactiveMongoClient() {
//        String myHost = System.getenv("MONGODB_HOST");
//        log.info("MONGODB_HOST="+myHost);
//        return MongoClients.create("mongodb://"+(myHost==null ? mongoHost : myHost)+":"+mongoPort);
//    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(mongoClient, "test");
    }
}
