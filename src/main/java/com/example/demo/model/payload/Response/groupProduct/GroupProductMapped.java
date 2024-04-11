package com.example.demo.model.payload.Response.groupProduct;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class GroupProductMapped {
    private String id;
    private String prd_group_name;
    private String parentid;
    private String level;
    private String lft;
    private String rgt;
    private LocalDate created;
    private LocalDate updated;
    private String user_init;
    private String user_upd;
    private String shop_id;

    public GroupProductMapped(String id,
                              String prd_group_name,
                              String parentid,
                              String level,
                              String lft,
                              String rgt,
                              LocalDate created,
                              String user_init,
                              String user_upd,
                              String shop_id) {
        this.id = id;
        this.prd_group_name = prd_group_name;
        this.parentid = parentid;
        this.level = level;
        this.lft = lft;
        this.rgt = rgt;
        this.created = created;
        this.updated = created;
        this.user_init = user_init;
        this.user_upd = user_upd;
        this.shop_id = shop_id;
    }
}
