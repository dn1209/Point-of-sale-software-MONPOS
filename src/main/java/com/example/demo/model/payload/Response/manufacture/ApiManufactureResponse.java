package com.example.demo.model.payload.Response.manufacture;

import lombok.Data;

import java.util.List;

@Data
public class ApiManufactureResponse {
    private String error_code;
    private int count_group;
    private List<ManufactureMapped> list_group;

    public ApiManufactureResponse(String error_code, int count_group, List<ManufactureMapped> groupProductMappedList) {
        this.error_code = error_code;
        this.count_group = count_group;
        this.list_group = groupProductMappedList;
    }
}
