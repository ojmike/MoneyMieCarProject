package com.moneymie.carproject.repository;

import com.moneymie.carproject.model.Car;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Long>, JpaSpecificationExecutor<Car> {
    Optional<Car> findByVin(String vin);

    @Query( value = " SELECT * FROM Car car " +
            "WHERE lower(car.brand) =?1 " +
            "OR lower(car.color)=?1 OR CAST(car.year AS varchar(10))= ?1 ",
    nativeQuery = true)
    List<Car> findByParameter(String searchParameter);

    // SELECT * FROM table
    //ORDER BY
    //CASE WHEN @Direction = 1 THEN -id else id END asc;

    @Query( value = "SELECT * FROM Car car ORDER BY ?1 ",
            nativeQuery = true)
    List<Car> findByParameterAndOrder(String searchParameter,String sortBy);





}
