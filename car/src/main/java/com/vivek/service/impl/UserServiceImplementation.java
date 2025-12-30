package com.vivek.service.impl;

import com.vivek.dto.CarResponseDTO;
import com.vivek.dto.UserRegistrationDTO;
import com.vivek.dto.UserResponseDTO;
import com.vivek.entity.Car;
import com.vivek.entity.User;
import com.vivek.exception.ResourceNotFoundException;
import com.vivek.repository.UserRepository;
import com.vivek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;

    private User mapToEntity(UserRegistrationDTO dto){
        User user=new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());
        user.setCity(dto.getCity());
        user.setPinCode(dto.getPinCode());
        user.setHouseNo(dto.getHouseNo());
        user.setPanNumber(dto.getPanNumber());
        return user;
    }

    private UserResponseDTO mapToResponseDTO(User user){
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
    private Page<UserResponseDTO> mapToResponseDTO(Page<User> users) {
        return users.map(this::mapToResponseDTO);
    }
    @Override
    public UserResponseDTO save(UserRegistrationDTO dto){
        return mapToResponseDTO(userRepository.save(mapToEntity(dto)));

    }

    @Override
    public UserResponseDTO update(UserRegistrationDTO dto){
        User user=new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());
        user.setCity(dto.getCity());
        user.setPinCode(dto.getPinCode());
        user.setHouseNo(dto.getHouseNo());
        user.setPanNumber(dto.getPanNumber());
        return mapToResponseDTO(user);
    }

    @Override
    public UserResponseDTO userFindById(int id){
        return mapToResponseDTO(userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with this Id does nto exist.")));
    }

    @Override
    public Page<UserResponseDTO> findAllUser(Pageable pageable){
        return mapToResponseDTO(userRepository.findAll(pageable));
    }

    @Override
    public List<UserResponseDTO> findByCity(String city){
        return userRepository.findByCity(city)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }
}
