package com.example.demo.service;

import com.example.demo.model.GroupProduct;
import com.example.demo.model.payload.Response.groupProduct.GroupProductMapped;
import com.example.demo.model.payload.Response.product.ProductMapped;
import com.example.demo.repository.GroupProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupProductService {
    @Autowired
    GroupProductRepository groupProductRepository;
    public GroupProduct save(GroupProduct groupProduct){
        return groupProductRepository.save(groupProduct);
    };

    public List<GroupProductMapped> getGroupProduct(Long storeId,Long userId){
        List<GroupProduct> groupProductList = groupProductRepository.getGroupProductByStoreId(storeId);
        List<GroupProductMapped> groupProductMappedList = groupProductList.stream().map(
                groupProduct -> new GroupProductMapped(
                        String.valueOf(groupProduct.getId()),
                        groupProduct.getPrdGroupName(),
                        String.valueOf(groupProduct.getParentId()),
                        String.valueOf(groupProduct.getLevel()),
                        "0",
                        "0",
                        groupProduct.getCreatedAt(),
                        String.valueOf(userId),
                        String.valueOf(userId),
                        String.valueOf(storeId)
                )).collect(Collectors.toList());
        return groupProductMappedList;
    }
}
