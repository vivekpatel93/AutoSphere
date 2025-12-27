package com.vivek.repository;

import com.vivek.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findByCity(User user);

    List<User> findByPinCode(int pinCode);

    List<User> findByCarModel(String model);

}
