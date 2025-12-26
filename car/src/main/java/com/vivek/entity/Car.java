package com.vivek.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="car")
public class Car {
    @Id
    @Column(name = "vinumber")
    private String vinNumber;
    private String companyName;
    private String carType;
    private String model;
    private String color;
    private double price;
    private int seatCapacity;
    private float mileage;

}
