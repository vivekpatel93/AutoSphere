package com.vivek.controller;

import com.vivek.dto.ChangePasswordDTO;
import com.vivek.dto.UserRegistrationDTO;
import com.vivek.dto.UserResponseDTO;
//import com.vivek.entity.User;
import com.vivek.entity.Purchase;
import com.vivek.repository.PurchaseRepository;
import com.vivek.service.CarUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    CarUserService carUserService;
    @Autowired
    PurchaseRepository purchaseRepository;

    @PostMapping("/save")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRegistrationDTO dto){
        return ResponseEntity.ok(carUserService.save(dto));
    }

    @PostMapping("/update")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRegistrationDTO dto){
        return ResponseEntity.ok(carUserService.update(dto));
    }
    @GetMapping("/purchases")
    public List<Purchase> myPurchase(){
        String email= SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        return purchaseRepository.findByBuyerEmail(email);
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDTO dto){
                carUserService.changePassword(dto);
                return ResponseEntity.ok("Password updated successfully");
    }

}
