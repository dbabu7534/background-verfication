package com.background_verification_app.backend.dto.request;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
