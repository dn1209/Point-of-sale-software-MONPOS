package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Bank {
    @Id
    @Column(unique = true)
    private String code_banks;
    private String name;
    private String name_banks;
    private String pin;
    private String card;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
