package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Data
public class Orders {
    @Id
    @SequenceGenerator(
            name = "orders_sequence",
            sequenceName = "orders_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orders_sequence"
    )
    private long id;
    private String outputCode;
    private int customerId;
    private LocalDate sellDate;
    private String notes;
    private int paymentMethod;
    private Float totalPrice;
    private Float totalOriginPrice;
    private int coupon;
    private Float customerPay;
    private Float totalMoney;
    private int totalQuantity;
    private Float lack;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<DetailOrder> detailOrders;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    private int orderStatus;
    private int deleted;
    private Float prdSellPrice;
    private int prdGroupId;
    private int prdStatus;
    private LocalDate createdAt;


}
