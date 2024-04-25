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
    private Float prdOriginalPrice;

    @ManyToOne
    @JoinColumn(name = "manufacture_id") // Tên cột khóa ngoại trong bảng Product
    private Manufacture manufacture;
    @ManyToOne
    @JoinColumn(name = "group_product_id") // Tên cột khóa ngoại trong bảng Product
    private GroupProduct groupProduct;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private int prdStatus;
    private LocalDate createdAt;

}
