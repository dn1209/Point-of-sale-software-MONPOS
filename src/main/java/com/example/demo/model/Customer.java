package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
@Data
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    private long id;
    private String customerCode;
    private String customerName;
    private String customerPhone;
    private String customerAddr;
    private Date sellDate;
    private Float totalMoney;
    private Float totalDebt;
    private LocalDate sellDateLast;
    private LocalDate createdAt;
    private Date birthday;
    private int customerGender;
    private String customerEmail;
    private int customerStatus;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

}
