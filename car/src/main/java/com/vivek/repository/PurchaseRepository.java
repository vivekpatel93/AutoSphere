package com.vivek.repository;

import com.vivek.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
    List<Purchase> findByBuyerEmail(String email);
}
