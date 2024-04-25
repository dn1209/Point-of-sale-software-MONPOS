package com.example.demo.model.payload.Response.customer;

import lombok.Data;

@Data

public class CustomerCount {
    private String total_money;
    private String total_debt;
    private String quantity;

    public CustomerCount(String total_money, String total_debt, String quantity) {
        this.total_money = total_money;
        this.total_debt = total_debt;
        this.quantity = quantity;
    }
}
