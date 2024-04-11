package com.example.demo.model.payload.Response.groupProduct;

import lombok.Data;

import java.util.List;

@Data
public class ApiGroupProductResponse {
    private String error_code;
    private int count_group;
    private List<GroupProductMapped> groupProductMappedList;

    public ApiGroupProductResponse(String error_code, int count_group, List<GroupProductMapped> groupProductMappedList) {
        this.error_code = error_code;
        this.count_group = count_group;
        this.groupProductMappedList = groupProductMappedList;
    }
}
