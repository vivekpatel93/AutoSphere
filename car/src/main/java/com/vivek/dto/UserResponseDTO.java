package com.vivek.dto;

import com.vivek.entity.Car;
import lombok.Data;

import java.util.List;
@Data
public class UserResponseDTO {
    private String name;
    private String email;
    private String phone;
    private String city;
    private String pinCode;
    private String houseNo;
    private String panNumber;
    private List<Car> cars;
}
