package com.example.demo.model.payload.Response.product;

import lombok.Data;

import java.util.List;

@Data
public class ApiProductResponse {
    private String error_code;
    private int count_products;
    private List<ProductMapped> list_orders;

    public ApiProductResponse(String error_code, int count_products, List<ProductMapped> list_orders) {
        this.error_code = error_code;
        this.count_products = count_products;
        this.list_orders = list_orders;
    }
}
