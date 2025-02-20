package com.background_verification_app.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Email is Required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

}
