package com.background_verification_app.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PersonalDetailDTO {
    private String name;
    private Integer age;
    private String idProof;
    private Date dateOfBirth;
}
