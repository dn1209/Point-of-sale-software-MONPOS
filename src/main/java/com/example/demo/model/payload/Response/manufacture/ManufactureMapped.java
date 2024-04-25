package com.example.demo.model.payload.Response.manufacture;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ManufactureMapped {
    private String id;
    private String prd_manuf_name;
    private LocalDate created;
    private LocalDate updated;
    private String user_init;
    private String user_upd;
    private String shop_id;
    public ManufactureMapped(String id,
                              String prd_manuf_name,
                              LocalDate created,
                              String user_init,
                              String user_upd,
                              String shop_id) {
        this.id = id;
        this.prd_manuf_name = prd_manuf_name;
        this.created = created;
        this.updated = created;
        this.user_init = user_init;
        this.user_upd = user_upd;
        this.shop_id = shop_id;
    }
}
