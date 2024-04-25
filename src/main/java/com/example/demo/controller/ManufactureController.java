package com.example.demo.controller;

import com.example.demo.model.payload.Response.ApiResponseError;
import com.example.demo.model.payload.Response.manufacture.ApiManufactureResponse;
import com.example.demo.model.payload.Response.manufacture.ManufactureMapped;
import com.example.demo.model.payload.authenticate.RegisterReponse;
import com.example.demo.model.payload.request.groupProduct.GroupProductRequest;
import com.example.demo.model.payload.request.manufacture.ManufactureRequest;
import com.example.demo.service.ManufactureService;
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
public class ManufactureController {
    @Autowired
    ManufactureService manufactureService;
    @Autowired
    UserServices userServices;
    @GetMapping("/manufacture")
    public ResponseEntity<?> getManu(HttpServletRequest request){
        List<ManufactureMapped> manufactureMappedList = manufactureService.getManufacture(
                userServices.getStoreIdByUserId(request));
        ApiManufactureResponse apiManufactureResponse = new ApiManufactureResponse("0",
                manufactureMappedList.size(),
                manufactureMappedList);
        return ResponseEntity.ok(apiManufactureResponse);

    }
    @PostMapping("/manufacture")
    public ResponseEntity<?> addManu(@Valid @RequestBody ManufactureRequest manufactureRequest, HttpServletRequest request){
//        if(groupProductRequest.getPrd_manuf_name().isEmpty())
        // spring boot se tu tao ra loi nma van nen kiem tra lai ( sau khi code hoa thian kiem tra lai doan nay)
        //kiem tra ca xem trong request gui len cai chuoi string co van de gi khong ( vi du nhu ?$#@!)
        if(manufactureService.checkName(manufactureRequest.getPrd_manuf_name())){
            ApiResponseError response = new ApiResponseError(HttpStatus.UNAUTHORIZED.value(), "nhà sản xuất đã tồn tại");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        manufactureService.saveWithService(
                manufactureRequest.getPrd_manuf_name(),
                userServices.getStoreIdByUserId(request),
                userServices.getUserIdByToken(request));
        return ResponseEntity.ok(new RegisterReponse("thêm mới nhà sản xuất thành công","0"));
    }
    @DeleteMapping("/manufacture/{id}")
    public ResponseEntity<?> deleteGroupProduct(@PathVariable Long id,HttpServletRequest request){
        if(!manufactureService.checkId(id)){
            ApiResponseError response = new ApiResponseError(HttpStatus.UNAUTHORIZED.value(), "nhà sản xuất không tồn tại ");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        manufactureService.deleteManufacture(id);
        return ResponseEntity.ok(new RegisterReponse("nhà sản xuất đã được xoá mềm","0"));
    }
}
