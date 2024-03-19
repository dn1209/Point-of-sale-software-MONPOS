package com.example.demo.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;
@Entity
@Table
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
    private Date logined;
    private String tokenLogin;

}
