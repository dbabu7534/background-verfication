package com.background_verification_app.backend.service.interfac;

import com.background_verification_app.backend.dto.Response;
import com.background_verification_app.backend.dto.request.EducationalDetailDTO;
import com.background_verification_app.backend.dto.request.PersonalDetailDTO;
import com.background_verification_app.backend.dto.request.ProfessionalDetailDTO;
import com.background_verification_app.backend.entity.EducationDetail;
import com.background_verification_app.backend.entity.PersonalDetail;
import com.background_verification_app.backend.entity.ProfessionalDetail;

public interface ICandidateService {
    Response savePersonalDetails(Integer id, PersonalDetailDTO personalDetail);
    Response saveEducationDetails(Integer id, EducationalDetailDTO educationDetail);
    Response saveProfessionalDetails(Integer id, ProfessionalDetailDTO professionalDetail);
    Response getUsersUploadDetails(Integer id);
}
