package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
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
    private int parentId;
    private int level;
    private LocalDate createdAt;
}
