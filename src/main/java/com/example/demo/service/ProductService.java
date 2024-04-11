package com.example.demo.service;
import java.util.stream.Collectors;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.model.payload.Response.product.ProductMapped;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<ProductMapped> getProductByKeyword(int status, String keyword){
        List<Product> productList = productRepository.findProductsByStatusAndKeyword(status,keyword);
        List<ProductMapped> productMappedList = productList.stream()
                .map(product -> new ProductMapped(
                        String.valueOf(product.getId()),
                        product.getPrdCode(),
                        product.getPrdName(),
                        product.getPrdSls().toString(),
                        product.getPrdSellPrice().toString(),
                        String.valueOf(product.getPrdGroupId()),
                        String.valueOf(product.getPrdManufactureId()),
                        "",
                        String.valueOf(product.getPrdStatus())
                ))
                .collect(Collectors.toList());
        //return productList;
        return productMappedList;

    }
}
