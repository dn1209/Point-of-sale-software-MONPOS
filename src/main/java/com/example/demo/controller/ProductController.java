package com.example.demo.controller;

import com.example.demo.model.payload.Response.ApiResponse;
import com.example.demo.model.payload.Response.ApiResponseError;
import com.example.demo.model.payload.Response.product.ApiProductDetailResponse;
import com.example.demo.model.payload.Response.product.ApiProductResponse;
import com.example.demo.model.payload.Response.product.ProductMapped;
import com.example.demo.model.payload.authenticate.RegisterReponse;
import com.example.demo.model.payload.request.product.ProductEditRequest;
import com.example.demo.model.payload.request.product.ProductNewRequest;
import com.example.demo.model.payload.request.product.ProductRequest;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Product;

import java.util.List;

@RestController
@RequestMapping("api_products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    UserServices userServices;
    //viet lai api nay them vao cai sotre ( groupProductId) (t
    //viet lai api nay hay them generatoken vao ( lay thong tin user tu token xong tu day lay store ra tu cai product day)
//    @GetMapping("/products")
//    public ResponseEntity<?> getProduct(@Valid @RequestBody ProductRequest productRequest,
//                                        HttpServletRequest request
//                                         ){
//
//        List<ProductMapped> products = productService.getProductByKeyword(Integer.parseInt(productRequest.getStatus_products()),
//                userServices.getStoreIdByUserId(request),
//                productRequest.getKeyword(),
//                productRequest.getGroup_product_id(),
//                productRequest.getManufacture_id());
//        ApiProductResponse apiProductResponse = new ApiProductResponse("0",products.size(),products);
//        return ResponseEntity.ok(apiProductResponse);
//    }
    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductNewRequest productNewRequest,
                                        HttpServletRequest request
    ){
        productService.addNewProduct(productNewRequest.getPrd_name(),
                productNewRequest.getPrd_code(),
                productNewRequest.getPrd_sls(),
                productNewRequest.getPrd_inventory(),
                productNewRequest.getPrd_origin_price(),
                productNewRequest.getPrd_sell_price(),
                productNewRequest.getPrd_group_id(),
                productNewRequest.getPrd_manufacture_id(),
                userServices.getStoreIdByUserId(request),
                userServices.getUserIdByToken(request)
                );

        return ResponseEntity.ok(new ApiResponse("thêm mới sản phẩm thành công","0"));
    }
//    @GetMapping("/products")
//    public ResponseEntity<?> getProductDetail(@PathVariable Long id,
//                                        HttpServletRequest request
//    ){
//        if(!productService.checkProduct(id)){
//            ApiResponseError response = new ApiResponseError(HttpStatus.UNAUTHORIZED.value(), "Sản phẩm không tồn tại vui lòng kiểm tra lại");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//        }
//        ApiProductDetailResponse api = productService.getDetailProduct(id,userServices.getStoreIdByUserId(request));
//        return ResponseEntity.ok(api);
//    }
    @GetMapping("/products")
    public ResponseEntity<?> getProductOrDetail(@RequestParam(required = false) Long id,
                                                @RequestParam(required = false) String   status_products,
                                                @RequestParam(required = false) String     group_product_id,
                                                @RequestParam(required = false) String    manufacture_id,
                                                @RequestParam(required = false) String         keyword,
                                                HttpServletRequest request) {

        // Kiểm tra xem có param id được truyền vào hay không
        if (id != null) {
            System.out.println("apii");

            // Xử lý khi có id được truyền vào
            if (!productService.checkProduct(id)) {
                ApiResponseError response = new ApiResponseError(HttpStatus.UNAUTHORIZED.value(), "Sản phẩm không tồn tại vui lòng kiểm tra lại");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            ApiProductDetailResponse api = productService.getDetailProduct(id, userServices.getStoreIdByUserId(request));
            System.out.println("apii");
            return ResponseEntity.ok(api);
        } else {
            // Xử lý khi không có id được truyền vào
            List<ProductMapped> products = productService.getProductByKeyword(
                    Integer.parseInt(status_products),
                    userServices.getStoreIdByUserId(request),
                    keyword,
                    group_product_id,
                    manufacture_id);
            ApiProductResponse apiProductResponse = new ApiProductResponse("0", products.size(), products);
            return ResponseEntity.ok(apiProductResponse);
        }
    }
    @PutMapping("/products/{id}")
    public ResponseEntity<?> putProductDetail(@PathVariable Long id,
            @Valid @RequestBody ProductEditRequest productEditRequest,
                                        HttpServletRequest request
    ){
        if(!productService.checkProduct(id)){
            ApiResponseError response = new ApiResponseError(HttpStatus.UNAUTHORIZED.value(), "Sản phẩm không tồn tại vui lòng kiểm tra lại");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

         productService.getAndEditProduct( productEditRequest,userServices.getStoreIdByUserId(request),id);
        return ResponseEntity.ok(new ApiResponse("sửa sản phẩm thành công","0"));
    }
    @PutMapping("/products_restore/{id}")
    public ResponseEntity<?> putProductRestore(@PathVariable Long id,
                                              HttpServletRequest request
    ){
        if(!productService.checkProduct(id)){
            ApiResponseError response = new ApiResponseError(HttpStatus.UNAUTHORIZED.value(), "Sản phẩm không tồn tại vui lòng kiểm tra lại");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        productService.updateProductStatus(id);
        return ResponseEntity.ok(new ApiResponse("Khôi phục sản phẩm thành công","0"));
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id,
                                               HttpServletRequest request
    ){
        if(!productService.checkProduct(id)){
            ApiResponseError response = new ApiResponseError(HttpStatus.UNAUTHORIZED.value(), "Sản phẩm không tồn tại vui lòng kiểm tra lại");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        productService.deleteSoftProductStatus(id);
        return ResponseEntity.ok(new ApiResponse("Xóa sản phẩm thành công","0"));
    }


}
