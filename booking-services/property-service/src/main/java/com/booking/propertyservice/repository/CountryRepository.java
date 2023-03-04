package com.booking.propertyservice.repository;

import com.booking.propertyservice.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM countries c WHERE c.name LIKE :name% order by c.id LIMIT 5")
    List<Country> findCountryByName(@Param("name") String name);

}
