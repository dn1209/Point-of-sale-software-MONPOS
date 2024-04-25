package com.example.demo.model.payload.Response.product;

import com.example.demo.model.Product;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductDetailData {
    private String ID;
    private String prd_code;
    private String prd_name;
    private String shop_id;
    private String prd_sls;
    private String prd_origin_price;
    private String prd_sell_price;
    private String prd_vat;
    private String prd_status;
    private String prd_inventory;
    private String prd_allownegative;
    private String prd_manufacture_id;
    private String prd_group_id;
    private String prd_image_url;
    private String prd_descriptions;
    private String prd_manuf_id;
    private String prd_hot;
    private String prd_new;
    private String prd_highlight;
    private String display_website;
    private LocalDate created;
    private LocalDate updated;

    public ProductDetailData(Product product) {
        this.ID = String.valueOf(product.getId());
        this.prd_code = product.getPrdCode();
        this.prd_name = product.getPrdName();
        this.shop_id = String.valueOf(product.getStore().getId());
        this.prd_sls = product.getPrdSls().toString();
        this.prd_origin_price = product.getPrdOriginalPrice().toString();
        this.prd_sell_price = product.getPrdSellPrice().toString();
        this.prd_vat = "0";
        this.prd_status = String.valueOf(product.getPrdStatus());
        this.prd_inventory = "0";
        this.prd_allownegative = "0";
        this.prd_manufacture_id = String.valueOf(product.getManufacture().getId());
        this.prd_group_id = String.valueOf(product.getStore().getId());
        this.prd_image_url = "";
        this.prd_descriptions = "";
        this.prd_manuf_id = String.valueOf(product.getManufacture().getId());
        this.prd_hot = "0";
        this.prd_new = "0";
        this.prd_highlight = "0";
        this.display_website = "0";
        this.created = product.getCreatedAt();
        this.updated = product.getCreatedAt();
        this.user_init = String.valueOf(product.getUser().getId());
        this.user_upd = String.valueOf(product.getUser().getId());
        this.deleted = String.valueOf(product.getPrdStatus());
    }

    private String user_init;
    private String user_upd;
    private String deleted;
}
