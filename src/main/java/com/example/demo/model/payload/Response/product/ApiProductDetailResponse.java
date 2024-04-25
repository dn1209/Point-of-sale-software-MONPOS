package com.example.demo.model.payload.Response.product;

import lombok.Data;

@Data
public class ApiProductDetailResponse {
    private String error_code;
    private ProductDetailData data_products;

    public ApiProductDetailResponse(String error_code, ProductDetailData data_products) {
        this.error_code = error_code;
        this.data_products = data_products;
    }

    public ApiProductDetailResponse(String error_code) {
        this.error_code = error_code;
    }
}
