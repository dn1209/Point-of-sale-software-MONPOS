package com.example.demo.model.payload.Response.product;

import com.example.demo.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductMapped {
    private String id;
    private String prd_code;
    private String prd_name;
    private String prd_sls;
    private String prd_sell_price;
    private String prd_group_id;
    private String prd_manufacture_id;
    private String prd_image_url;
    private String prd_status;

    public ProductMapped(String id,
                         String prd_code,
                         String prd_name,
                         String prd_sls,
                         String prd_sell_price,
                         String prd_group_id,
                         String prd_manufacture_id,
                         String prd_image_url,
                         String prd_status) {
        this.id = id;
        this.prd_code = prd_code;
        this.prd_name = prd_name;
        this.prd_sls = prd_sls;
        this.prd_sell_price = prd_sell_price;
        this.prd_group_id = prd_group_id;
        this.prd_manufacture_id = prd_manufacture_id;
        this.prd_image_url = prd_image_url;
        this.prd_status = prd_status;
    }
}
