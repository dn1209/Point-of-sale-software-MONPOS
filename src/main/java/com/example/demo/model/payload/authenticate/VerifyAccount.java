package com.example.demo.model.payload.authenticate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VerifyAccount {
    @NotBlank
    private String email;
    @NotBlank
    private String verificationCode;
}
