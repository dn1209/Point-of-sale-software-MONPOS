package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table
public class Manufacture {
    @Id
    @SequenceGenerator(
            name = "manufacture_sequence",
            sequenceName = "manufacture_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "manufacture_sequence"
    )
    private long id;
    private String prdManufName;
    private LocalDate created;
    private int userInit;
    private int userUpd;
    private int shopId;
}
