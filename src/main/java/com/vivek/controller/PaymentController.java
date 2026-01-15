package com.vivek.controller;

import com.vivek.dto.PaymentResponseDTO;
import com.vivek.entity.PaymentStatus;
import com.vivek.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    public final PaymentService paymentService;
    @Autowired
    public PaymentController(PaymentService paymentService){
        this.paymentService=paymentService;
    }
    @GetMapping("/me")
    public ResponseEntity<List<PaymentResponseDTO>> getMyPayment(){
        return ResponseEntity.ok(paymentService.getMyPayments());
    }
    @GetMapping("/admin/all")
    public ResponseEntity<List<PaymentResponseDTO>> getPaymentsByStatus(PaymentStatus status){
        return ResponseEntity.ok(paymentService.getPaymentsByStatus(status));
    }
}
