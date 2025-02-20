package com.background_verification_app.backend.mapper;

import com.background_verification_app.backend.dto.request.*;
import com.background_verification_app.backend.dto.response.AllCandidateStatusDTO;
import com.background_verification_app.backend.entity.*;

public class Mapper {

    public static User mapToUser(UserRegisterDTO userRegisterDTO){
        User user = new User(
                userRegisterDTO.getFirstName(),
                userRegisterDTO.getLastName(),
                userRegisterDTO.getEmail(),
                userRegisterDTO.getPassword()
        );
        return user;
    }

    public static PersonalDetail mapToPersonal(PersonalDetailDTO personalDetailDTO){
        PersonalDetail personalDetail = new PersonalDetail(
                personalDetailDTO.getName(),
                personalDetailDTO.getAge(),
                personalDetailDTO.getIdProof(),
                personalDetailDTO.getDateOfBirth()
        );
        return personalDetail;
    }

    public static ProfessionalDetail mapToProfessional(ProfessionalDetailDTO professionalDetailDTO){
        ProfessionalDetail professionalDetail = new ProfessionalDetail(
                professionalDetailDTO.getCompanyName(),
                professionalDetailDTO.getPreviousCompanyRole(),
                professionalDetailDTO.getYearOfExperience(),
                professionalDetailDTO.getDate()
        );
        return professionalDetail;
    }

    public static EducationDetail mapToEducational(EducationalDetailDTO educationalDetailDTO){
        return new EducationDetail(
                educationalDetailDTO.getSslcSchoolName(),
                educationalDetailDTO.getSslcPercentage(),

                educationalDetailDTO.getHslcSchoolName(),
                educationalDetailDTO.getHslcPercentage(),

                educationalDetailDTO.getCollegeName(),
                educationalDetailDTO.getUniversityRollNumber(),
                educationalDetailDTO.getFinalCgpa()
        );
    }

    public static CandidateStatus mapToCandidateStatus(UserStatusDTO user){
        CandidateStatus candidateStatus = new CandidateStatus();

        candidateStatus.setCandidateId(user.getCandidateId());
        candidateStatus.setStatus(user.getStatus());

        return candidateStatus;
    }

    public static AllCandidateStatusDTO mapToAllCandidateStatus(Object email, Object name, Object status){
        AllCandidateStatusDTO candidateStatusDTO = new AllCandidateStatusDTO();
        candidateStatusDTO.setEmail(email.toString());
        candidateStatusDTO.setName(name.toString());
        candidateStatusDTO.setStatus(status.toString());

        return candidateStatusDTO;
    }


}
