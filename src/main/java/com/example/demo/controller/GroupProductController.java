package com.example.demo.controller;

import com.example.demo.model.payload.Response.ApiResponseError;
import com.example.demo.model.payload.Response.groupProduct.ApiGroupProductResponse;
import com.example.demo.model.payload.Response.groupProduct.GroupProductMapped;
import com.example.demo.model.payload.authenticate.RegisterReponse;
import com.example.demo.model.payload.request.groupProduct.GroupProductRequest;
import com.example.demo.service.GroupProductService;
import com.example.demo.service.UserServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api_products")
public class GroupProductController {
    @Autowired
    GroupProductService groupProductService;
    @Autowired
    UserServices userServices;
    @GetMapping("/group_products")
    public ResponseEntity<?> getGroupProduct(HttpServletRequest request){
        List<GroupProductMapped> groupProductMappedList = groupProductService.getGroupProduct(
                userServices.getStoreIdByUserId(request));
        ApiGroupProductResponse apiGroupProductResponse = new ApiGroupProductResponse("0",
                groupProductMappedList.size(),
                groupProductMappedList);
           return ResponseEntity.ok(apiGroupProductResponse);

    }
    @PostMapping("/group_products")
    public ResponseEntity<?> addGroupProduct(@Valid @RequestBody GroupProductRequest groupProductRequest,HttpServletRequest request){
//        if(groupProductRequest.getPrd_manuf_name().isEmpty())
        // spring boot se tu tao ra loi nma van nen kiem tra lai ( sau khi code hoa thian kiem tra lai doan nay)
        //kiem tra ca xem trong request gui len cai chuoi string co van de gi khong ( vi du nhu ?$#@!)
        if(groupProductService.checkName(groupProductRequest.getPrd_group_name())){
            ApiResponseError response = new ApiResponseError(HttpStatus.UNAUTHORIZED.value(), "Danh mục đã tồn tại");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        groupProductService.saveWithService(groupProductRequest.getPrd_group_name(),
                userServices.getStoreIdByUserId(request),
                Integer.parseInt(groupProductRequest.getParentid()),
                userServices.getUserIdByToken(request));
        return ResponseEntity.ok(new RegisterReponse("thêm mới danh mục thành công","0"));
    }
    @DeleteMapping("/group_products/{id}")
    public ResponseEntity<?> deleteGroupProduct(@PathVariable Long id,HttpServletRequest request){
        if(!groupProductService.checkId(id)){
            ApiResponseError response = new ApiResponseError(HttpStatus.UNAUTHORIZED.value(), "Danh muc không tồn tại ");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        groupProductService.deleteGroupProduct(id);
        return ResponseEntity.ok(new RegisterReponse("Danh mục đã được xoá mềm","0"));
    }
//    public
}
