package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;
@Entity
@Table
@Data
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private long id;
    private String userName;
    private String password;
    private String email;
    private String displayName;
    private String userStatus;
    private int groupId;
    private int storeId;
    private LocalDate created;
    private Date updated;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Store store;
    private Date logined;
    private String tokenLogin;

    public User(String userName, String password, String email, String userStatus, LocalDate created, Date updated, Store store, Date logined) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userStatus = userStatus;
        this.created = created;
        this.updated = updated;
        this.store = store;
        this.logined = logined;
    }

    public User() {

    }
}
