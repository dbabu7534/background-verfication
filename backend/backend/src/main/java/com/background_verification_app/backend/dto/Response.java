package com.background_verification_app.backend.dto;

import com.background_verification_app.backend.dto.request.EducationalDetailDTO;
import com.background_verification_app.backend.dto.request.FieldStatusDTO;
import com.background_verification_app.backend.dto.request.PersonalDetailDTO;
import com.background_verification_app.backend.dto.request.ProfessionalDetailDTO;
import com.background_verification_app.backend.dto.response.AllCandidateStatusDTO;
import com.background_verification_app.backend.dto.response.AllFieldResponseDTO;

import com.background_verification_app.backend.dto.response.UserDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private int statusCode;
    private String message;


    private String token;
    private String role;
    private String expirationTime;

    private UserDTO user;

    private EducationalDetailDTO education;
    private PersonalDetailDTO personal;
    private ProfessionalDetailDTO professional;

    private Integer id;

    private List<String> pageOrders;

    private Object personalField; // Add this field

    private Object professionalField;

    private Object educationalField;

    private List<AllFieldResponseDTO> getAllUsers;

    private Map<String, Object>  uploadedDocuments;

    private List<FieldStatusDTO> activeFields;

    private List<AllCandidateStatusDTO> candidateStatus;

    private List<Object> uploaded;

}
