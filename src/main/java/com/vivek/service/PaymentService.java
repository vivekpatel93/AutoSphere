package com.vivek.service;

import com.vivek.dto.PaymentResponseDTO;
import com.vivek.entity.Account;
import com.vivek.entity.Payment;
import com.vivek.entity.PaymentStatus;
import com.vivek.entity.Purchase;

import java.util.List;

public interface PaymentService {
    Payment createPayment(Account account, Purchase purchase, double amount, String method);

    PaymentResponseDTO getPaymentId(Long paymentId);

    List<PaymentResponseDTO> getMyPayments();

    // for Admin
    List<PaymentResponseDTO> getPaymentsByStatus(PaymentStatus status);

    // void refundPayment(Long paymentId);
}
