package com.example.demo.model.payload.Response.customer;

import lombok.Data;

import java.util.List;

@Data

public class ApiCustomerResponse {
    private String error_code;
    private CustomerCount count_customer;
    private List<ListCustomer> list_customer;

    public ApiCustomerResponse(String error_code, CustomerCount count_customer, List<ListCustomer> list_customer) {
        this.error_code = error_code;
        this.count_customer = count_customer;
        this.list_customer = list_customer;
    }
}
