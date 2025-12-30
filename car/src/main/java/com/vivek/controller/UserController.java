package com.vivek.controller;

import com.vivek.dto.UserRegistrationDTO;
import com.vivek.dto.UserResponseDTO;
import com.vivek.entity.User;
import com.vivek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("/save")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRegistrationDTO dto){
        return ResponseEntity.ok(userService.save(dto));
    }

    @PostMapping("/update")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRegistrationDTO dto){
        return ResponseEntity.ok(userService.update(dto));
    }

}
