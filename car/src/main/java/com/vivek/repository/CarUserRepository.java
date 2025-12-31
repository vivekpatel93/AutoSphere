package com.vivek.repository;

import com.vivek.entity.CarUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarUserRepository extends JpaRepository<CarUser,Integer> {

    List<CarUser> findByCity(String city);

    List<CarUser> findByPinCode(String pinCode);
    Optional<CarUser> findByEmail(String email);

}
