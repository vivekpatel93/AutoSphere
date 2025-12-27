package com.vivek.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
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

    @Size(min = 4,max =4,message = "Invalid pin code.")
    private int pinCode;

    private String houseNo;

    private String panNumber;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Car> cars;
}
