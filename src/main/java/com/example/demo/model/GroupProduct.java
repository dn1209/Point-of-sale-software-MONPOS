package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Data
public class GroupProduct {
    @Id
    @SequenceGenerator(
            name = "groupProduct_sequence",
            sequenceName = "groupProduct_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "groupProduct_sequence"
    )
    private long id;
    private String prdGroupName;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "groupProduct")
    private List<Product> products;
    private int parentId;
    private int level;
    private int status;
    private LocalDate createdAt;
}
