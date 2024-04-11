package com.example.demo.controller;

import com.example.demo.model.payload.Response.product.ApiProductResponse;
import com.example.demo.model.payload.Response.product.ProductMapped;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Product;

import java.util.List;

@RestController
@RequestMapping("api_products")
public class ProductController {
    @Autowired
    ProductService productService;
    //viet lai api nay them vao cai sotre ( groupProductId) (t
    //viet lai api nay hay them generatoken vao ( lay thong tin user tu token xong tu day lay store ra tu cai product day)
    public ResponseEntity<?> getProduct( @RequestParam(name = "status_products", required = true) int statusProducts,
                                         @RequestParam(name = "group_product_id", required = false) long groupProductId,
                                         @RequestParam(name = "manufacture_id", required = false) long manufactureId,
                                         @RequestParam(name = "keyword", required = false) String keyword){
        List<ProductMapped> products = productService.getProductByKeyword(statusProducts,keyword);
        ApiProductResponse apiProductResponse = new ApiProductResponse("0",products.size(),products);
        return ResponseEntity.ok(apiProductResponse);
    }
}
