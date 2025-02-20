//package com.background_verification_app.backend.service.impl;
//
//import com.background_verification_app.backend.dto.EducationDTO;
//import com.background_verification_app.backend.dto.PersonalDTO;
//import com.background_verification_app.backend.dto.request.ProfessionalDetailDTO;
//import com.background_verification_app.backend.dto.Response;
//import com.background_verification_app.backend.entity.EducationDetail;
//import com.background_verification_app.backend.entity.PersonalDetail;
//import com.background_verification_app.backend.entity.ProfessionalDetail;
//import com.background_verification_app.backend.entity.User;
//import com.background_verification_app.backend.exception.OwnException;
//import com.background_verification_app.backend.repo.EducationRepository;
//import com.background_verification_app.backend.repo.PersonalRepository;
//import com.background_verification_app.backend.repo.ProfessionalRepository;
//import com.background_verification_app.backend.repo.UserRepository;
//import com.background_verification_app.backend.service.interfac.ICandidateService;
//import com.background_verification_app.backend.utils.Utils;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CandidateServi implements ICandidateService {
//
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private EducationRepository educationRepository;
//    @Autowired
//    private PersonalRepository personalRepository;
//    @Autowired
//    private ProfessionalRepository professionalRepository;
//
//    @Transactional
//    @Override
//    public Response savePersonalDetails(Integer id, PersonalDetail personalDetail) {
//        Response response = new Response();
////        try{
////
////            // Fetch the User entity by ID
////            User user = userRepository.findById(id)
////                    .orElseThrow(() -> new OwnException("User not found with ID: " + id));
////
////            // Handle existing PersonalDetail (if any)
////            PersonalDetail existingPersonalDetail = user.getPersonalDetail();
////
////
////            if (existingPersonalDetail != null) {
////                personalDetail.setId(existingPersonalDetail.getId()); // Reuse existing ID
////            }
////
////
////            // Set the user in PersonalDetail
////            personalDetail.setUser(user);
////            PersonalDetail savedPersonalDetail = personalRepository.save(personalDetail);
////
////            // Update the User entity with the saved PersonalDetail
////            user.setPersonalDetail(savedPersonalDetail);
////            userRepository.save(user);
////
////
////            PersonalDTO personalDTO = Utils.mapPersonalToPersonalDTO(savedPersonalDetail);
////
////            response.setStatusCode(200);
////            response.setPersonal(personalDTO);
////            response.setMessage("successful");
//
//        try {
//
//        } catch (OwnException e){
//            response.setStatusCode(404);
//            response.setMessage(e.getMessage());
//        } catch (Exception e) {
//            response.setStatusCode(500);
//            response.setMessage("Error Saving a User" + e.getMessage());
//        }
//
//        return response;
//
//    }
//
//
//    @Override
//    public Response saveEducationDetails(Integer id, EducationDetail educationDetail) {
//        Response response = new Response();
//
//        try{
//            var user = userRepository.findById(id).orElseThrow( ()-> new OwnException("User Not Found"));
//            EducationDetail savedEducationDetail = educationRepository.save(educationDetail);
//
//
//            EducationDTO educationDTO = Utils.mapEducationToEducationDTO(savedEducationDetail);
//
//            response.setStatusCode(200);
//            response.setEducation(educationDTO);
//            response.setMessage("successful");
//        } catch (OwnException e){
//            response.setStatusCode(200);
//            response.setMessage(e.getMessage());
//        } catch (Exception e) {
//            response.setStatusCode(500);
//            response.setMessage("Error Saving a User" + e.getMessage());
//        }
//
//        return response;
//    }
//
//
//    @Override
//    public Response saveProfessionalnDetails(ProfessionalDetail professionalDetail) {
//        Response response = new Response();
//        ProfessionalDetail savedProfessionalDetail = professionalRepository.save(professionalDetail);
//
//        ProfessionalDetailDTO professionalDTO = Utils.mapProfessionalToProfessionalDTO(savedProfessionalDetail);
//
//        response.setStatusCode(200);
//        response.setProfessional(professionalDTO);
//        response.setMessage("successful");
//        return response;
//    }
//}
