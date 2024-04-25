package com.example.demo.model.payload.request.customer;

import lombok.Data;

@Data
public class CustomerRequest {
    private String customer_code;
    private String customer_name;
    private String customer_phone;
    private String customer_email;
    private String customer_addr;
    private String notes;
    private String customer_birthday;
    private String customer_gender;
}
