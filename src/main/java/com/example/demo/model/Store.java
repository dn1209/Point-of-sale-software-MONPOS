package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<User> users;
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<GroupProduct> groupProducts;
     public Store(){
    }


    public Store(String name) {
        this.userName = name;

    }
}
