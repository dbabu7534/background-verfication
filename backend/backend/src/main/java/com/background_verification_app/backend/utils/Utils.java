package com.background_verification_app.backend.utils;


import com.background_verification_app.backend.dto.request.ProfessionalDetailDTO;
import com.background_verification_app.backend.dto.response.UserDTO;
import com.background_verification_app.backend.entity.EducationDetail;
import com.background_verification_app.backend.entity.PersonalDetail;
import com.background_verification_app.backend.entity.ProfessionalDetail;
import com.background_verification_app.backend.entity.User;

public class Utils {

    public static UserDTO mapUserEntityToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        return userDTO;
    }

//    public static EducationalDTO mapEducationToEducationDTO(EducationDetail educationDetail){
//        EducationDTO educationDTO = new EducationDTO();
//        educationDTO.setId(educationDetail.getId());
//        return educationDTO;
//    }
//
//    public static PersonalDTO mapPersonalToPersonalDTO(PersonalDetail personalDetail){
//        PersonalDTO personalDTO = new PersonalDTO();
//        personalDTO.setId(personalDetail.getId());
//        return personalDTO;
//    }
//
//    public static ProfessionalDetailDTO mapProfessionalToProfessionalDTO(ProfessionalDetail professionalDetail){
//        ProfessionalDetailDTO professionalDetailDTO = new ProfessionalDetailDTO();
//        professionalDetailDTO.setId(professionalDetail.getId());
//        return professionalDetailDTO;
//    }

}
