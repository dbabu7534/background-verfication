package com.background_verification_app.backend.dto.response;

import com.background_verification_app.backend.dto.request.EducationalDetailDTO;
import com.background_verification_app.backend.dto.request.PersonalDetailDTO;
import com.background_verification_app.backend.dto.request.ProfessionalDetailDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class AllFieldResponseDTO {


    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;

    private PersonalDetailDTO personalDetail;
    private EducationalDetailDTO educationalDetail;
    private ProfessionalDetailDTO professionalDetail;



}
