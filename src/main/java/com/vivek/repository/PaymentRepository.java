package com.vivek.repository;

import com.vivek.dto.PaymentResponseDTO;
import com.vivek.entity.Payment;
import com.vivek.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
        List<Payment> findAllByAccount_Id(Long accountId);

        List<Payment> findAllByStatus(PaymentStatus status);


}
