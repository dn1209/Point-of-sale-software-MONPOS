package com.example.demo.model.payload.request.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private String sale_id;
    private String customer_id;
    private String sell_date;// Ngày tháng đơn hàng lên dưới dạng  Y-m-d H:i:s
    private String notes;
    private String payment_method;
    private String coupon;
    private String customer_pay;
    private List<DetailOrderRequest> detail_order;
    private String order_status;

}
