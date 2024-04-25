package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.GroupProduct;
import com.example.demo.model.Store;
import com.example.demo.model.User;
import com.example.demo.model.payload.Response.groupProduct.GroupProductMapped;
import com.example.demo.model.payload.Response.product.ProductMapped;
import com.example.demo.repository.GroupProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupProductService {
    @Autowired
    GroupProductRepository groupProductRepository;
    @Autowired
    StoreService storeService;
    @Autowired
    UserServices userServices;
    public GroupProduct save(GroupProduct groupProduct){
        return groupProductRepository.save(groupProduct);
    };
    public GroupProduct findById(Long id){
        Optional<GroupProduct> gr = groupProductRepository.findByGroupProductId(id);
            if( gr.isEmpty()){
                throw new UserNotFoundException("Store not found with username: " + id);
            }
        GroupProduct grD = gr.get();
        return grD;
    }
    public GroupProduct saveWithService(String name, Long id,int parentId,Long userId){
        GroupProduct gr = new GroupProduct();
        gr.setPrdGroupName(name);
        Store store = storeService.findStoreById(id);
        User user = userServices.getUserById(userId);
        gr.setParentId(parentId);
        if(parentId != -1){
            gr.setLevel(1);
        }
        gr.setUser(user);
        gr.setStore(store);
        return groupProductRepository.save(gr);
    }

    public List<GroupProductMapped> getGroupProduct(Long storeId){
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
                        String.valueOf(groupProduct.getUser().getId()),
                        String.valueOf(groupProduct.getUser().getId()),
                        String.valueOf(groupProduct.getStore().getId())
                )).collect(Collectors.toList());
        return groupProductMappedList;
    }

    public boolean checkName(String name){
        return groupProductRepository.existsByName(name);
    }
    public boolean checkId(Long id){
        return groupProductRepository.existsById(id);
    }
    public void deleteGroupProduct(Long id){
        groupProductRepository.updateGroupProductById(id);
    }
}
