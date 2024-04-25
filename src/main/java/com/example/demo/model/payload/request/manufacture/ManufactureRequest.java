package com.example.demo.model.payload.request.manufacture;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ManufactureRequest {
    @NotBlank
    private String prd_manuf_name;
}
