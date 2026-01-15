package com.vivek.service.impl;

import com.vivek.dto.PaymentResponseDTO;
import com.vivek.entity.*;
import com.vivek.exception.InsufficientBalanceException;
import com.vivek.exception.ResourceNotFoundException;
import com.vivek.repository.AccountRepository;
import com.vivek.repository.CarUserRepository;
import com.vivek.repository.PaymentRepository;
import com.vivek.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final AccountRepository accountRepository;
    private final PaymentRepository paymentRepository;
    private final CarUserRepository carUserRepository;

    @Autowired
    public PaymentServiceImpl(AccountRepository accountRepository,
                              PaymentRepository paymentRepository,
                              CarUserRepository carUserRepository){

        this.accountRepository=accountRepository;
        this.paymentRepository=paymentRepository;
        this.carUserRepository=carUserRepository;
    }

    // mapper
    private PaymentResponseDTO entityToDTO(Payment payment){
        PaymentResponseDTO dto=new PaymentResponseDTO();
        dto.setAmount(payment.getAmount());
        dto.setStatus(payment.getStatus());
        dto.setPaidAt(payment.getPaidAt());
        return dto;
    }

    @Override
    public Payment createPayment(Account account, Purchase purchase, double amount, String method){

           if(account.getBalance() < amount){
                throw new InsufficientBalanceException("Insufficient balance in account: " + account.getAccountNo());
           }

        // Deduct balance
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        // Create payment record
        Payment payment = new Payment();
        payment.setAccount(account);
        payment.setPurchase(purchase);
        payment.setAmount(amount);
        payment.setMethod(method);
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setPaidAt(LocalDateTime.now());

        // Save payment
        return paymentRepository.save(payment);
    }

    @Override
    public PaymentResponseDTO getPaymentId(Long paymentId){
        return entityToDTO(paymentRepository.findById(paymentId).orElseThrow(()-> new ResourceNotFoundException("Payment not exist.")));
    }

    @Override
    public List<PaymentResponseDTO> getMyPayments(){
        String email= SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        CarUser carUser = carUserRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User does not exist."));
        return paymentRepository.getAllPaymentsOfUser(carUser.getUserId());
    }

    @Override
    public List<PaymentResponseDTO> getPaymentsByStatus(PaymentStatus status){
        return paymentRepository.getPaymentByStatus(status);
    }


}
