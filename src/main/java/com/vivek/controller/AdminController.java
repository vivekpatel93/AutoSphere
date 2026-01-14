package com.vivek.controller;
import java.util.List;

import com.vivek.entity.Car;
import com.vivek.entity.CarUser;
import com.vivek.entity.Purchase;
import com.vivek.repository.CarRepository;
import com.vivek.repository.CarUserRepository;
import com.vivek.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CarUserRepository carUserRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @GetMapping("/users")
    public List<CarUser> getAllUsers(){
        return carUserRepository.findAll();
    }

    @GetMapping("/cars")
    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    @GetMapping("/purchases")
    public List<Purchase> showAllPurchases(){
        return purchaseRepository.findAll();
    }
}
