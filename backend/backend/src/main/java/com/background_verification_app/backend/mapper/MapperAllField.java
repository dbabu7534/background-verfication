package com.background_verification_app.backend.mapper;

import com.background_verification_app.backend.dto.request.EducationalDetailDTO;
import com.background_verification_app.backend.dto.request.PersonalDetailDTO;
import com.background_verification_app.backend.dto.request.ProfessionalDetailDTO;
import com.background_verification_app.backend.dto.response.AllFieldResponseDTO;
import com.background_verification_app.backend.dto.response.AllFieldResponseDTO;
import com.background_verification_app.backend.entity.PersonalDetail;
import com.background_verification_app.backend.entity.User;

public class MapperAllField {
    public static AllFieldResponseDTO mapToUserResponseDTO(User user) {
        return AllFieldResponseDTO.builder()
                .userId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())

                // Map Personal Details
                .personalDetail(user.getPersonalDetail() != null ?
                        PersonalDetailDTO.builder()
                                .name(user.getPersonalDetail().getName())
                                .age(user.getPersonalDetail().getAge())
                                .idProof(user.getPersonalDetail().getIdProof())
                                .dateOfBirth(user.getPersonalDetail().getDateOfBirth())
                                .build()
                        : null)

                // Map Educational Details
                .educationalDetail(user.getEducationDetails() != null ?
                        EducationalDetailDTO.builder()
                                .sslcSchoolName(user.getEducationDetails().getSslcSchoolName())
                                .sslcPercentage(user.getEducationDetails().getSslcPercentage())
                                .hslcSchoolName(user.getEducationDetails().getHslcSchoolName())
                                .hslcPercentage(user.getEducationDetails().getHslcPercentage())
                                .collegeName(user.getEducationDetails().getCollegeName())
                                .universityRollNumber(user.getEducationDetails().getUniversityRollNumber())
                                .finalCgpa(user.getEducationDetails().getFinalCgpa())
                                .build()
                        : null)

                // Map Professional Details
                .professionalDetail(user.getProfessionalDetail() != null ?
                        ProfessionalDetailDTO.builder()
                                .companyName(user.getProfessionalDetail().getCompanyName())
                                .previousCompanyRole(user.getProfessionalDetail().getPreviousCompanyRole())
                                .yearOfExperience(user.getProfessionalDetail().getYearOfExperience())
                                .date(user.getProfessionalDetail().getDateOfJoined())
                                .build()
                        : null)
                .build();
    }
}


