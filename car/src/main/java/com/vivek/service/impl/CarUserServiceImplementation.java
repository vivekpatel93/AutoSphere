package com.vivek.service.impl;

import com.vivek.dto.PurchaseResponseDTO;
import com.vivek.dto.UserRegistrationDTO;
import com.vivek.dto.UserResponseDTO;
import com.vivek.entity.Car;
import com.vivek.entity.CarUser;
import com.vivek.exception.ResourceNotFoundException;
import com.vivek.repository.CarRepository;
import com.vivek.repository.CarUserRepository;
import com.vivek.service.CarUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarUserServiceImplementation implements CarUserService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarUserRepository carUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private CarUser mapToEntity(UserRegistrationDTO dto){
        CarUser user=new CarUser();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setPhone(dto.getPhone());
        user.setCity(dto.getCity());
        user.setPinCode(dto.getPinCode());
        user.setHouseNo(dto.getHouseNo());
        user.setPanNumber(dto.getPanNumber());
        return user;
    }

    private UserResponseDTO mapToResponseDTO(CarUser user){
        UserResponseDTO userResponseDTO=new UserResponseDTO();
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setPhone(user.getPhone());
        userResponseDTO.setCity(user.getCity());
        userResponseDTO.setPinCode(user.getPinCode());
        userResponseDTO.setHouseNo(user.getHouseNo());
        userResponseDTO.setPanNumber(user.getPanNumber());
        userResponseDTO.setCars(user.getCars());

        return userResponseDTO;
    }
    private Page<UserResponseDTO> mapToResponseDTO(Page<CarUser> users) {
        return users.map(this::mapToResponseDTO);
    }


    @Override
    public UserResponseDTO save(UserRegistrationDTO dto){
        return mapToResponseDTO(carUserRepository.save(mapToEntity(dto)));

    }

    @Override
    public UserResponseDTO update(UserRegistrationDTO dto){
//        CarUser user=carUserRepository.findById(id).
//                orElseThrow(()-> new ResourceNotFoundException("User not found with this Id."));
//
//        user.setName(dto.getName());
//        user.setEmail(dto.getEmail());
//
//        if(dto.getPassword() != null && !dto.getPassword().isBlank()) {
//            user.setPassword(passwordEncoder.encode(dto.getPassword()));
//        }
//        user.setPhone(dto.getPhone());
//        user.setCity(dto.getCity());
//        user.setPinCode(dto.getPinCode());
//        user.setHouseNo(dto.getHouseNo());
//        user.setPanNumber(dto.getPanNumber());
//        return mapToResponseDTO(carUserRepository.save(user));
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        CarUser user = carUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // update only allowed fields
        user.setName(dto.getName());
        user.setCity(dto.getCity());
        user.setPhone(dto.getPhone());
        user.setPinCode(dto.getPinCode());
        user.setHouseNo(dto.getHouseNo());

        carUserRepository.save(user);

        UserResponseDTO res = new UserResponseDTO();
        res.setName(user.getName());
        res.setEmail(user.getEmail());
        res.setPhone(user.getPhone());
        res.setCity(user.getCity());
        res.setPinCode(user.getPinCode());
        res.setHouseNo(user.getHouseNo());
        res.setPanNumber(user.getPanNumber());
        res.setCars(user.getCars());

        return res;
    }

    @Override
    public UserResponseDTO userFindById(int id){
        return mapToResponseDTO(carUserRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with this Id does nto exist.")));
    }

    @Override
    public Page<UserResponseDTO> findAllUser(Pageable pageable){
        return mapToResponseDTO(carUserRepository.findAll(pageable));
    }

    @Override
    public List<UserResponseDTO> findByCity(String city){
        return carUserRepository.findByCity(city)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

}
