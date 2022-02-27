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
            " inner join addresses c on p.address_id = c.id" +
            " WHERE" +
            " p.max_guest_number >= :guestNumber" +
            " and" +
            " p.address_id in" +
            " (SELECT a.id FROM addresses a inner join countries c on a.country_id = c.id WHERE c.name = :location)" +
            " and" +
            " p.id not in :propertyIds")
    List<Property> searchProperties(
            @Param("propertyIds") List<Long> propertyIds,
            @Param("location") String location,
            @Param("guestNumber") int guestNumber,
            Pageable pageable
            );

    @Query(nativeQuery = true, value = "SELECT count(*) FROM properties p" +
            " inner join addresses c on p.address_id = c.id" +
            " WHERE" +
            " p.max_guest_number >= :guestNumber" +
            " and" +
            " p.address_id in" +
            " (SELECT a.id FROM addresses a inner join countries c on a.country_id = c.id WHERE c.name = :location)" +
            " and" +
            " p.id not in :propertyIds")
    Long count(
            @Param("propertyIds") List<Long> propertyIds,
            @Param("location") String location,
            @Param("guestNumber") int guestNumber
    );

    @Query(nativeQuery = true, value = "SELECT * from properties where owner = :ownerId")
    List<Property> findByOwner(@Param("ownerId") String ownerId, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT count(*) from properties where owner = :ownerId")
    Long count(String ownerId);

    Property getPropertyById(Long propertyId);
}

