package com.vivek.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String city;
    private String pinCode;
    private String houseNo;
    private String panNumber;
}
