package com.example.demo.model.payload.request.product;

import lombok.Data;

@Data
public class ProductEditRequest {
    private String prd_name;
    private Float prd_sls;
    private Float prd_inventory;
    private Float prd_origin_price;
    private Float prd_sell_price;
    private Long prd_group_id;
    private int prd_status;
    private Long prd_manufacture_id;

}
