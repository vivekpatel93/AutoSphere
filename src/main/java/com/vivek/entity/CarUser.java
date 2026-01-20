package com.vivek.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class CarUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq",initialValue = 2025001,allocationSize = 1)
    private int userId;

    @Column(nullable = false)
    @Size(min=2)
    private String name;

    @Column(unique = true)
    private String email;

    @Size(min=10,max = 10, message = "Invalid phone number.")
    private String phone;

    @Column(nullable = false)
    @Size(min = 8)
    private String password;

    private String city;

    @Size(min = 6,max =6,message = "Invalid pin code.")
    private String pinCode;

    private String houseNo;

    private String panNumber;
    // for role base access
    private String role;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Car> cars;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;
}
