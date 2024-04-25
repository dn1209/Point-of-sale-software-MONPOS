package com.example.demo.model.payload.Response.orders;

import com.example.demo.model.payload.request.order.DetailOrderRequest;
import lombok.Data;

import java.util.List;

@Data
public class ListOrders {
    public ListOrders(String ID,
                      String output_code,
                      String customer_id,
                      String store_id,
                      String sell_date,
                      String notes,
                      String payment_method,
                      String total_price,
                      String total_origin_price,
                      String coupon,
                      String customer_pay,
                      String total_money,
                      String total_quantity,
                      String lack,
                      String order_status,
                      String deleted,
                      String created, String updated, String user_init, String user_upd, String sale_id, String shop_id,
                      List<DetailOrderRequest> detail_order) {
        this.ID = ID;
        this.output_code = output_code;
        this.customer_id = customer_id;
        this.store_id = store_id;
        this.sell_date = sell_date;
        this.notes = notes;
        this.payment_method = payment_method;
        this.total_price = total_price;
        this.total_origin_price = total_origin_price;
        this.coupon = coupon;
        this.customer_pay = customer_pay;
        this.total_money = total_money;
        this.total_quantity = total_quantity;
        this.lack = lack;
        this.order_status = order_status;
        this.deleted = deleted;
        this.created = created;
        this.updated = updated;
        this.user_init = user_init;
        this.user_upd = user_upd;
        this.sale_id = sale_id;
        this.shop_id = shop_id;
        this.sell_time = "0";
        this.detail_order = detail_order;
    }

    private String ID;
    private String output_code;
    private String customer_id;
    private String store_id;
    private String sell_date;
    private String notes;
    private String payment_method;
    private String total_price;
    private String total_origin_price;
    private String coupon;
    private String customer_pay;
    private String total_money;
    private String total_quantity;
    private String lack;
    private String order_status;
    private String deleted;
    private String created;
    private String updated;
    private String user_init;
    private String user_upd;
    private String sale_id;
    private String shop_id;
    private String sell_time;
    private List<DetailOrderRequest> detail_order;


}
