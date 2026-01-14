package com.vivek.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "bank_id_seq")
    @SequenceGenerator(name = "bank_id_seq",initialValue = 910000000,allocationSize = 1)
    private Long id;

    @Column(unique = true,nullable = false)
    private String accountNo;

    private Double balance;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false,unique = true)
    private CarUser carUser;


    // 🔹 One account → Many payments
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Payment> payments;
}
