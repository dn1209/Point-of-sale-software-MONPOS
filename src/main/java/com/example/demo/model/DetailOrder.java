package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class DetailOrder {
    @Id
    @SequenceGenerator(
            name = "detailOrder_sequence",
            sequenceName = "detailOrder_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "detailOrder_sequence"
    )
    private long id;
    private int quantity;
    private int price;
    private int discount;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;
}
