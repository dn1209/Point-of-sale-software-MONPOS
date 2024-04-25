package com.example.demo.model.payload.request.groupProduct;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GroupProductRequest {
    @NotBlank
    private String prd_group_name;
    private String parentid;
}
