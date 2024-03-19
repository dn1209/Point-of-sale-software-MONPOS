package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
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
    private Float customerPhone;
    private String customerAddr;
    private Date sellDate;
    private Float totalMoney;
    private Float totalDebt;
    private LocalDate createdAt;

}
