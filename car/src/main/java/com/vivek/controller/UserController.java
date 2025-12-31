package com.vivek.controller;

import com.vivek.dto.UserRegistrationDTO;
import com.vivek.dto.UserResponseDTO;
//import com.vivek.entity.User;
import com.vivek.service.CarUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    CarUserService carUserService;


    @PostMapping("/save")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRegistrationDTO dto){
        return ResponseEntity.ok(carUserService.save(dto));
    }

    @PostMapping("/update")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRegistrationDTO dto){
        return ResponseEntity.ok(carUserService.update(dto));
        // Hello my name is vivek patel and now i am working as fullstack intern at qspider
    }

}
