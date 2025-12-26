package com.vivek.repository;

import com.vivek.dto.CarResponseDTO;
import com.vivek.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    @Query("select c from Car c where c.color=?1")
    List<Car> findByColor(String color);

    @Query("select c from Car c where c.companyName= ?1")
    List<Car> findByCompanyName(String companyName);

    @Query("select c from Car c where c.model=?1")
    List<Car> findByModelName(String model);

    @Query("select c from Car c where c.mileage> ?1")
    List<Car> findByMileage(float mileage);
}
