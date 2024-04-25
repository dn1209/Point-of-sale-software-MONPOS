package com.example.demo.controller;

import com.example.demo.model.payload.Response.ApiResponse;
import com.example.demo.model.payload.Response.customer.ApiCustomerResponse;
import com.example.demo.model.payload.Response.customer.ListCustomer;
import com.example.demo.model.payload.request.customer.CustomerRequest;
import com.example.demo.model.payload.request.product.ProductRequest;
import com.example.demo.service.CustomerService;
import com.example.demo.service.UserServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("api_products")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    UserServices userServices;
    @GetMapping("/customer")
    public ResponseEntity<?> getCustomer(@RequestParam(required = false) int status_customer,
                                                @RequestParam(required = false) String keyword,
                                                HttpServletRequest request){
        ApiCustomerResponse apiCustomerResponse = customerService.getCustomerByStoreId(userServices.getStoreIdByUserId(request),keyword );
        return ResponseEntity.ok(apiCustomerResponse);

    }
    @PostMapping("/customer")
    public ResponseEntity<?> postCustomer(@Valid @RequestBody CustomerRequest customerRequest,HttpServletRequest request) throws ParseException {
        customerService.saveCustomer(userServices.getStoreIdByUserId(request),
                customerRequest.getCustomer_name(),
                customerRequest.getCustomer_code(),
                customerRequest.getCustomer_phone(),
                customerRequest.getCustomer_email(),
                customerRequest.getCustomer_addr(),
                customerRequest.getCustomer_gender(),
                customerRequest.getCustomer_birthday()
                );
        return ResponseEntity.ok(new ApiResponse("thêm mới khách hàng thành công","0"));

    }
    @DeleteMapping("/customer")
    public ResponseEntity<?> deleteCustomer(@RequestParam Long id,HttpServletRequest request){
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(new ApiResponse("xoa khách hàng thành công","0"));

    }
}
