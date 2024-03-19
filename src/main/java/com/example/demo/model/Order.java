package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
@Entity
@Table
public class Order {
    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    private long id;
    private String outputCode;
    private int customerId;
    private int storeId;
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
    private int orderStatus;
    private int deleted;
    private Float prdSellPrice;
    private int prdGroupId;
    private int prdStatus;
    private LocalDate createdAt;
    public void addDetailOrder(DetailOrder detailOrder) {
        detailOrders.add(detailOrder);
        detailOrder.setOrder(this);
    }

}
