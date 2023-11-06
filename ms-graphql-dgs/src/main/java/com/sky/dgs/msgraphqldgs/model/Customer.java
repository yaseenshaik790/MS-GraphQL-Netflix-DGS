package com.sky.dgs.msgraphqldgs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Customer")
public class Customer {

    @Id
    @Column(name = "CUSTOMER_NUMBER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerNumber;
    private String name;
    private String gender;
    private Long contact;
    private String mail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Account> accounts;
}
