package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Entity
@Table
@Data
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private long id;
    private String prdCode;
    private String prdName;
    private Float prdSls;
    private Float prdSellPrice;
    private int prdGroupId;
    private int prdManufactureId;
    private int prdStatus;
    private LocalDate createdAt;

}
