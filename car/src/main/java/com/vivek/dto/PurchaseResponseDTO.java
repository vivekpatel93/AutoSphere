package com.vivek.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResponseDTO {
    // We are returning dto not any real entity to maintain security

    private String message;
    private String buyerName;
    private String carModel;
    private String vin;
    private double price;
    private LocalDateTime purchaseDate;
}
