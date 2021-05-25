package com.booking.propertyservice.repository;

import com.booking.propertyservice.model.Property;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends PagingAndSortingRepository<Property, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM properties p" +
            " WHERE" +
            " p.max_guest_number >= :guestNumber" +
            " and" +
            " p.id not in :propertyIds"
//            " and" +
//            " p.address_id in" +
//            " (SELECT a.id FROM addresses a inner join countries c on a.country_id = c.id WHERE c.name = :location)" +
//            " and" +
//            " (DATE(:checkIn) not between r.check_in and r.check_out" +
//            " or" +
//            " DATE(:checkOut) not between r.check_in and r.check_out)" +
//            " and" +
//            " (r.check_in not between DATE(:checkIn)  and DATE(:checkOut)" +
//            " or" +
//            " r.check_out not between DATE(:checkIn)  and DATE(:checkOut))"
    )
    List<Property> searchProperties(
            @Param("propertyIds") List<Long> propertyIds,
            @Param("guestNumber") int guestNumber,
            Pageable pageable
            );

    @Query(nativeQuery = true, value = "SELECT count(*) FROM properties p" +
            " WHERE" +
            " p.max_guest_number >= :guestNumber" +
            " and" +
            " p.id in :propertyIds")
    Long count(
            @Param("propertyIds") List<Long> propertyIds,
            @Param("guestNumber") int guestNumber
    );

    @Query(nativeQuery = true, value = "SELECT * from properties where owner = :ownerId")
    List<Property> findByOwner(@Param("ownerId") String ownerId, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT count(*) from properties where owner = :ownerId")
    Long count(String ownerId);

//    @Query(nativeQuery = true, value = "SELECT * FROM properties p inner join users u on p.user_id = u.id where p.user_id = :id")
//    Set<Property> findByUser(@Param("id") Long id);

}

