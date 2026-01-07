package com.vivek.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CarResponseDTO {
    private String vinNumber;
    private String companyName;
    private String carType;
    private String model;
    private String color;
    private double price;
    private int seatCapacity;
    private float mileage;
    private OwnerDTO owner;
}
