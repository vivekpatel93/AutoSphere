package com.vivek.dto;

import com.vivek.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDTO {
    private Double amount;
    private PaymentStatus status;
    private LocalDateTime paidAt;

}
