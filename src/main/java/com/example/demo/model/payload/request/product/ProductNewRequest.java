package com.example.demo.model.payload.request.product;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductNewRequest {
    private String prd_name;
    private String prd_code;
    @NotBlank
    private String prd_sls;
    private String prd_inventory;
    private String prd_origin_price;
    private String prd_sell_price;
    private String prd_group_id;
    private String prd_manufacture_id;

}
