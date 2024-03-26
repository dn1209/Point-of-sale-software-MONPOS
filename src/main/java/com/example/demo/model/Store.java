package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Store {
    @Id
    @SequenceGenerator(
            name = "store_sequence",
            sequenceName = "store_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "store_sequence"
    )
    private long id;
    private String userName;
    @OneToOne(mappedBy = "store", cascade = CascadeType.ALL)
    private User user;



}
