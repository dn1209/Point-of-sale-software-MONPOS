package com.example.demo.model.payload.Response.orders;

import lombok.Data;

@Data
public class OrderCount {
    private String quantity;
    private String total_money;
    private String total_debt;

    public OrderCount(String quantity, String total_money, String total_debt) {
        this.quantity = quantity;
        this.total_money = total_money;
        this.total_debt = total_debt;
    }
}
