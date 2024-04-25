package com.example.demo.model.payload.request.order;

import lombok.Data;

@Data
public class DetailOrderRequest {
    private String id;
    private String quantity;
    private String price;
    private String discount;

    public DetailOrderRequest(String id, String quantity, String price, String discount) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }
}
