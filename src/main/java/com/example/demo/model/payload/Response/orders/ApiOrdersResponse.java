package com.example.demo.model.payload.Response.orders;

import lombok.Data;

import java.util.List;

@Data
public class ApiOrdersResponse {
    private String error_code;
    private OrderCount data_orders;
    private List<ListOrders> list_orders;

    public ApiOrdersResponse(String error_code, OrderCount data_orders, List<ListOrders> list_orders) {
        this.error_code = error_code;
        this.data_orders = data_orders;
        this.list_orders = list_orders;
    }
}
