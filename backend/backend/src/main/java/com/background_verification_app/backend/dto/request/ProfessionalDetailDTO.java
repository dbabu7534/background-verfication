package com.background_verification_app.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ProfessionalDetailDTO {
    private String companyName;
    private String previousCompanyRole;
    private Integer yearOfExperience;
    private Date date;
}
