package com.vivek.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "payment_id_seq")
    @SequenceGenerator(name = "payment_id_seq",initialValue = 510000000,allocationSize = 1)
    private Long id;

    private Double amount;
    private String method;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private LocalDateTime paidAt;

    //  One payment → One purchase
    @OneToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    //  Many payments → One account
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


}
