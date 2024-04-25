package com.example.demo.service;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.*;
import com.example.demo.model.payload.Response.product.ApiProductDetailResponse;
import com.example.demo.model.payload.Response.product.ProductDetailData;
import com.example.demo.model.payload.Response.product.ProductMapped;
import com.example.demo.model.payload.request.product.ProductEditRequest;
import com.example.demo.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    GroupProductService groupProductService;
    @Autowired
    ManufactureService manufactureService;
    @Autowired
    StoreService storeService;
    @Autowired
    UserServices userServices;
    @PersistenceContext
    private EntityManager entityManager;
    public List<ProductMapped> getProductByKeyword(int status,Long id, String keyword,String group_product_id,String manufacture_id){
        List<Product> productList = new ArrayList<>();
        if(!group_product_id.isEmpty() && !manufacture_id.isEmpty() ){
            productList = productRepository.findProductsByStatusAndKeywordAndManuAndGroupProduct(status,
                    id,
                    keyword,
                    Long.parseLong(manufacture_id),
                    Long.parseLong(group_product_id));

        }else if(!group_product_id.isEmpty()){
            productList = productRepository.findProductsByStatusAndKeywordAndGroupProduct(status,id,keyword,Long.parseLong(group_product_id));

        }else if(!manufacture_id.isEmpty()){
            productList = productRepository.findProductsByStatusAndKeywordAndManu(status,id,keyword,Long.parseLong(manufacture_id));
        }else{
            productList = productRepository.findProductsByStatusAndKeyword(status,id,keyword);
        }
        List<ProductMapped> productMappedList = productList.stream()
                .map(product -> new ProductMapped(
                        String.valueOf(product.getId()),
                        product.getPrdCode(),
                        product.getPrdName(),
                        product.getPrdSls().toString(),
                        String.valueOf(product.getPrdSellPrice().intValue()),
                        String.valueOf(product.getGroupProduct().getId()),
                        String.valueOf(product.getManufacture().getId()),
                        "",
                        String.valueOf(product.getPrdStatus())
                ))
                .collect(Collectors.toList());
        //return productList;
        return productMappedList;

    }
    @Transactional
    public Long getNextProductIdSequence() {
        Object result = entityManager.createNativeQuery("SELECT NEXT VALUE FOR product_sequence").getSingleResult();
        if (result != null) {
            return ((Number) result).longValue();
        } else {
            throw new RuntimeException("Failed to get next value from sequence");
        }
    }
    public Product addNewProduct(String prd_name,
                                 String prd_code,
                                 String prd_sls,
                                 String prd_inventory,
                                 String prd_origin_price,
                                 String prd_sell_price,
                                 String prd_group_id,
                                 String prd_manufacture_id,
                                 Long sId,
                                 Long uId){
         LocalDate today = LocalDate.now();
        String a =prd_code;
        if(prd_code.isEmpty()){
             a = "PD" + String.format("%05d", (productRepository.getLastProductId() +1)); // Ví dụ mã PD00001, PD00002, ...
        }
        Product product = new Product();
        product.setPrdName(prd_name);
        product.setPrdCode(a);
        product.setPrdSls(Float.valueOf(prd_sls));
        product.setPrdSellPrice(Float.valueOf(prd_sell_price));
        product.setPrdOriginalPrice(Float.valueOf(prd_origin_price));
        GroupProduct gr = groupProductService.findById(Long.parseLong(prd_group_id));
        Manufacture manufacture = manufactureService.findById(Long.parseLong(prd_manufacture_id));
        Store store = storeService.findStoreById(sId);
        User user = userServices.getUserById(uId);
        product.setGroupProduct(gr);
        product.setManufacture(manufacture);
        product.setStore(store);
        product.setUser(user);
        product.setCreatedAt(today);
        product.setPrdStatus(0);
        return productRepository.save(product);
    }
    public Product findByIdAndStore(Long id, Long sId){
        Optional<Product> product = productRepository.findByIdAndStore(id,sId);
        if(product.isEmpty()){
            throw new UserNotFoundException("khong co product");
        }
        Product pr = product.get();
        return pr;
    }
    public Product findByIdAndName(String id, Long sId){
        Optional<Product> product = productRepository.findByIdAndName(id,sId);
        if(product.isEmpty()){
            throw new UserNotFoundException("khong co product");
        }
        Product pr = product.get();
        return pr;
    }
    @Transactional
    public void getAndEditProduct(ProductEditRequest productEditRequest,Long id,Long pId){
        Product pr = findByIdAndStore(pId,id );
        GroupProduct  gr = groupProductService.findById(productEditRequest.getPrd_group_id());
        Manufacture manufacture = manufactureService.findById(productEditRequest.getPrd_manufacture_id());
        productRepository.updateProductById(
                productEditRequest.getPrd_name(),
                productEditRequest.getPrd_sls(),
                productEditRequest.getPrd_origin_price(),
                productEditRequest.getPrd_sell_price(),
                gr,
                manufacture,
                productEditRequest.getPrd_status(),
                pr.getId()
        );
    }
    @Transactional
    public void updateProductWithPRDSLS(Float sl,Long id){
        productRepository.updateProductWithPRDSLS(sl,id);
    }
    @Transactional
    public void updateProductStatus(Long id){
        productRepository.updateProduct(id);
    }
    @Transactional
    public void deleteSoftProductStatus(Long id){
        productRepository.updateProduct(id);
    }
    public boolean checkProduct(Long id){
        return productRepository.existsById(id);
    }
    public boolean checkProductByName(String name){
        return productRepository.existsByName(name);
    }
    public boolean checkProductWithSLS(Long id, int quantity){
        return productRepository.checkProductWithIdAndQuantity(id, quantity);
    }
    public ApiProductDetailResponse getDetailProduct(Long id, Long sId){
        Product pr = findByIdAndStore(id,sId);
        ProductDetailData productDetailData = new ProductDetailData(pr);
        ApiProductDetailResponse apiProductDetailResponse = new ApiProductDetailResponse("0",productDetailData);
        return apiProductDetailResponse;
    }

}
