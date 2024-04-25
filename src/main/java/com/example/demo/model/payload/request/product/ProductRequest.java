package com.example.demo.model.payload.request.product;

import lombok.Data;

@Data
public class ProductRequest {
    private String status_products;
    private String group_product_id;
    private String manufacture_id;
    private String keyword;

}
