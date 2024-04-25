package com.example.demo.controller;

import com.example.demo.model.DetailOrder;
import com.example.demo.model.payload.Response.ApiResponse;
import com.example.demo.model.payload.Response.ApiResponseError;
import com.example.demo.model.payload.Response.orders.ApiOrdersResponse;
import com.example.demo.model.payload.request.order.DetailOrderRequest;
import com.example.demo.model.payload.request.order.OrderRequest;
import com.example.demo.model.payload.request.product.ProductNewRequest;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("api_products")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;
    @Autowired
    UserServices userServices;
    @PostMapping("/orders")
    public ResponseEntity<?> addNewOrder(@Valid @RequestBody OrderRequest orderRequest,
                                        HttpServletRequest request
    ){
        for (DetailOrderRequest orderRequest1 : orderRequest.getDetail_order()) {
            if(productService.checkProductWithSLS(Long.parseLong(orderRequest1.getId()),Integer.parseInt(orderRequest1.getQuantity()))){
                ApiResponseError response = new ApiResponseError(HttpStatus.UNAUTHORIZED.value(), "Số lượng sản phẩm không đủ");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        }
        orderService.saveOrder(orderRequest,userServices.getStoreIdByUserId(request));


        return ResponseEntity.ok(new ApiResponse("thêm mới sản phẩm thành công","0"));
    }
    @GetMapping("/orders")
    public ResponseEntity<?> getAllOrder(
            @RequestParam(name = "starts_date") Long startsDate,
            @RequestParam(name = "ends_date") Long endsDate,
            @RequestParam(name = "status_orders") Integer statusOrders,
            @RequestParam(name = "keyword") String keyword,
            HttpServletRequest request){
        LocalDate startsDateTime = convertTimestampToDateTime(startsDate);
        LocalDate endsDateTime = convertTimestampToDateTime(endsDate);
        ApiOrdersResponse apiOrdersResponse = orderService.getListOrder(startsDateTime,endsDateTime,userServices.getStoreIdByUserId(request));
        return ResponseEntity.ok(apiOrdersResponse);


    }
    private LocalDate convertTimestampToDateTime(long timestamp) {
        return LocalDate.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
    }

}
