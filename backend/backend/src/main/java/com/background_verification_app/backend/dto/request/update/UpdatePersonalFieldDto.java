package com.background_verification_app.backend.dto.request.update;

import lombok.Data;

@Data
public class UpdatePersonalFieldDto {
    private String field;
    private Boolean isActive;
}

