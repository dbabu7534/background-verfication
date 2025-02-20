package com.background_verification_app.backend.dto.request;

import lombok.Data;

@Data
public class UserStatusDTO {
    private Integer candidateId;
    private String status;
}
