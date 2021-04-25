package com.booking.propertyservice.repository;

import com.booking.propertyservice.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PropertyRepository extends CrudRepository<Property, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM properties p" +
            " inner join countries c on p.country_id = c.id" +
            " inner join reservations r on p.id = r.property_id" +
            " WHERE" +
            " p.max_guest_number >= :guestNumber" +
            " and" +
            " c.name = :location" +
            " and" +
            " (DATE(:checkIn) not between r.check_in and r.check_out" +
            " or" +
            " DATE(:checkOut) not between r.check_in and r.check_out)" +
            " and" +
            " (r.check_in not between DATE(:checkIn)  and DATE(:checkOut)" +
            " or" +
            " r.check_out not between DATE(:checkIn)  and DATE(:checkOut))"
    )
    List<Property> searchProperties(
            @Param("location") String location,
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut,
            @Param("guestNumber") int guestNumber,
            Pageable pageable
            );

    List<Property> findAllByOwner_id(String ownerId);
//
//    @Query(nativeQuery = true, value = "SELECT * FROM properties p inner join users u on p.user_id = u.id where p.user_id = :id")
//    Set<Property> findByUser(@Param("id") Long id);

}

