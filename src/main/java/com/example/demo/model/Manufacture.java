package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.time.LocalDate;
@Entity
@Table
@Data
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
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "manufacture")
    private List<Product> products;
    private int status;
    private int userInit;
    private int userUpd;
    private int shopId;
}
