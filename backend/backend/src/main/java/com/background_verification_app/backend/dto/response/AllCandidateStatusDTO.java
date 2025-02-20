package com.background_verification_app.backend.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AllCandidateStatusDTO {
    private String name;
    private String email;
    private String status;
}
