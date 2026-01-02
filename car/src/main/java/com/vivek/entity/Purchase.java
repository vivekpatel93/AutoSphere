package com.vivek.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vin;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private CarUser buyer;

    @OneToOne
    @JoinColumn(name = "car_vin")
    private Car car;

    private double price;
    private LocalDateTime purchaseDate;
}
