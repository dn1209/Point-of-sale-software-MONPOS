package com.example.demo.controller;

import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.model.payload.Response.groupProduct.ApiGroupProductResponse;
import com.example.demo.model.payload.Response.groupProduct.GroupProductMapped;
import com.example.demo.service.GroupProductService;
import com.example.demo.service.UserServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api_products")
public class GroupProductController {
    @Autowired
    GroupProductService groupProductService;
    @Autowired
    UserServices userServices;
    public ResponseEntity<?> getGroupProduct(HttpServletRequest request){
        List<GroupProductMapped> groupProductMappedList = groupProductService.getGroupProduct(userServices.getStoreIdByUserId(request),
                userServices.getUserIdByToken(request));
        ApiGroupProductResponse apiGroupProductResponse = new ApiGroupProductResponse("0",
                groupProductMappedList.size(),
                groupProductMappedList);
           return ResponseEntity.ok(apiGroupProductResponse);

    }
}
