package com.example.demo.model.payload.Response.customer;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ListCustomer {
    private String ID;
    private String customer_code;
    private String customer_name;
    private String customer_phone;
    private String customer_addr;
    private LocalDate sell_date;
    private String total_money;
    private String total_debt;

    public ListCustomer(String ID,
                        String customer_code,
                        String customer_name,
                        String customer_phone,
                        String customer_addr,
                        LocalDate sell_date,
                        String total_money,
                        String total_debt) {
        this.ID = ID;
        this.customer_code = customer_code;
        this.customer_name = customer_name;
        this.customer_phone = customer_phone;
        this.customer_addr = customer_addr;
        this.sell_date = sell_date;
        this.total_money = total_money;
        this.total_debt = total_debt;
    }
}
