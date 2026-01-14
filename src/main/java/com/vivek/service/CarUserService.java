package com.vivek.service;

import com.vivek.dto.ChangePasswordDTO;
import com.vivek.dto.PurchaseResponseDTO;
import com.vivek.dto.UserRegistrationDTO;
import com.vivek.dto.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CarUserService {
    // save user
    UserResponseDTO save(UserRegistrationDTO dto);
    // update user
    UserResponseDTO update(UserRegistrationDTO dto);
    // find user by ID
    UserResponseDTO userFindById(int id);
    // pagination
    Page<UserResponseDTO> findAllUser(Pageable pageable);
    // find by city
    List<UserResponseDTO> findByCity(String city);
    // change password
    void changePassword(ChangePasswordDTO dto);


}
