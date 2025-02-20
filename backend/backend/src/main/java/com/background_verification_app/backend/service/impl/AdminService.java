package com.background_verification_app.backend.service.impl;

import com.background_verification_app.backend.dto.Response;
import com.background_verification_app.backend.dto.request.FieldStatusDTO;
import com.background_verification_app.backend.dto.request.UserStatusDTO;
import com.background_verification_app.backend.dto.response.AllCandidateStatusDTO;
import com.background_verification_app.backend.dto.response.AllFieldResponseDTO;
import com.background_verification_app.backend.entity.AdminField;
import com.background_verification_app.backend.entity.CandidateStatus;
import com.background_verification_app.backend.entity.User;
import com.background_verification_app.backend.exception.OwnException;
import com.background_verification_app.backend.mapper.Mapper;
import com.background_verification_app.backend.mapper.MapperAllField;
import com.background_verification_app.backend.repo.AdminFieldRepository;
import com.background_verification_app.backend.repo.CandidateStatusRepository;
import com.background_verification_app.backend.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdminService {


    @Autowired
    private AdminFieldRepository adminFieldRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CandidateStatusRepository candidateStatusRepository;

    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);


    @PreAuthorize("hasRole('ADMIN')")
    public Response getPersonalFields() {
        Response response = new Response();

        try {
            List<FieldStatusDTO> personalFields = adminFieldRepository.findAllFields("personal_detail");
            System.out.println(personalFields);
            response.setStatusCode(200); // Success status code
            response.setMessage("Personal fields fetched successfully");
            response.setPersonalField(personalFields);
        } catch (OwnException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Fetching Personal Fields" + e.getMessage());
        }

        return response;
    }

    public Response getProfessionalFields(){
        Response response = new Response();
        try {
            List<FieldStatusDTO> professionalDetail = adminFieldRepository.findAllFields("professional_detail");
            System.out.println(professionalDetail);
            response.setStatusCode(200); // Success status code
            response.setMessage("Professional fields fetched successfully");
            response.setProfessionalField(professionalDetail);
        } catch (OwnException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Fetching Professional Fields" + e.getMessage());
        }

        return response;
    }

    public Response getEducationalFields(){
        Response response = new Response();
        try {
            List<FieldStatusDTO> educationalDetail = adminFieldRepository.findAllFields("educational_detail");
            System.out.println(educationalDetail);
            response.setStatusCode(200); // Success status code
            response.setMessage("Educational fields fetched successfully");
            response.setEducationalField(educationalDetail);
        } catch (OwnException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Fetching educational Fields" + e.getMessage());
        }

        return response;
    }


    @Transactional(rollbackFor = TransactionException.class)
    public Response updatedFields(List<FieldStatusDTO> updatedList) {
        Response response = new Response();
        try {
            for (FieldStatusDTO fieldStatusDTO : updatedList) {
                Optional<AdminField> existingField = adminFieldRepository.findByField(fieldStatusDTO.getField());

                if (existingField.isPresent()) {
                    AdminField field = existingField.get();
                    if (!Objects.equals(field.getIsActive(), fieldStatusDTO.getIsActive())) {  // Avoid unnecessary updates
                        field.setIsActive(fieldStatusDTO.getIsActive());
                        adminFieldRepository.save(field);
                    }
                } else {
                    logger.warn("Field not found for entity: {}", fieldStatusDTO.getField());
                }
            }

            response.setStatusCode(200);
            response.setMessage("Updated Successfully");
        } catch (DataAccessException dae) {  // Database-related issues
            logger.error("Database error: {}", dae.getMessage(), dae);
            response.setStatusCode(500);
            response.setMessage("Database error: " + dae.getMessage());
            throw dae;  // Ensure transaction rollback happens explicitly
        } catch (OwnException e) {  // Custom exception
            logger.error("Business logic error: {}", e.getMessage(), e);
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {  // General Exception Handling
            logger.error("Unexpected error: {}", e.getMessage(), e);
            response.setStatusCode(500);
            response.setMessage("Error Updating Personal Fields: " + e.getMessage());
            throw new RuntimeException("Transaction rollback due to unexpected error", e);
        }
        return response;
    }

    public Response getAllUsers(){
        Response response = new Response();
        try{
            List<AllFieldResponseDTO> listOfDto = new ArrayList<>();

            List<User> users = userRepository.findAll();
            for(User user : users){
                if(user.getRole().equals("USER")){
                    AllFieldResponseDTO dto = MapperAllField.mapToUserResponseDTO(user);
                    listOfDto.add(dto);
                }
            }
            response.setGetAllUsers(listOfDto);
            response.setStatusCode(200);
            response.setMessage("Successfully Fetched All List of Users");

        } catch (DataAccessException dae) {  // Database-related issues
            logger.error("Database error: {}", dae.getMessage(), dae);
            response.setStatusCode(500);
            response.setMessage("Database error: " + dae.getMessage());
            throw dae;  // Ensure transaction rollback happens explicitly
        } catch (OwnException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Fetching educational Fields" + e.getMessage());
        }
        return response;
    }

    @Transactional(rollbackFor = TransactionException.class)
    public Response userStatus(UserStatusDTO candidate) {

        Response response = new Response();

        try {
            CandidateStatus candidateStatus = Mapper.mapToCandidateStatus(candidate);

            if (candidateStatusRepository.existsByCandidateId(candidateStatus.getCandidateId())) {
                Optional<CandidateStatus> existingCandidate = candidateStatusRepository.findByCandidateId(candidateStatus.getCandidateId());
                System.out.println(existingCandidate);
                if (existingCandidate.isPresent()) {
                    CandidateStatus status = existingCandidate.get();
                    status.setStatus(candidateStatus.getStatus());
                }
                response.setStatusCode(200);
                response.setMessage("Candidate Status Updated");
                return response;
            }

            candidateStatusRepository.save(candidateStatus);
            response.setStatusCode(200);
            response.setMessage("Candidate Status Saved");
            return response;

        } catch (DataAccessException dae) {  // Database-related issues
            logger.error("Database error: {}", dae.getMessage(), dae);
            response.setStatusCode(500);
            response.setMessage("Database error: " + dae.getMessage());
            throw dae;  // Ensure transaction rollback happens explicitly
        } catch (OwnException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Fetching educational Fields" + e.getMessage());
        }

        return response;
    }

//    public Response allUserStatus(){
//        candidateStatusRepository.
//    }

    public Response getUserStatus() {
        Response response = new Response();
        try{
            List<Object[]> results = candidateStatusRepository.fetchUserStatus();
            List<AllCandidateStatusDTO> dto = new ArrayList<>();
            for (Object[] row : results) {
                AllCandidateStatusDTO candidateStatusDTO = Mapper.mapToAllCandidateStatus(row[1], row[0], row[2]);
                dto.add(candidateStatusDTO);
            }
            response.setCandidateStatus(dto);

            response.setStatusCode(200);
            response.setMessage("All Candidate Users Status Fetched Successfully");
            return response;
        } catch (DataAccessException dae) {  // Database-related issues
            logger.error("Database error: {}", dae.getMessage(), dae);
            response.setStatusCode(500);
            response.setMessage("Database error: " + dae.getMessage());
            throw dae;  // Ensure transaction rollback happens explicitly
        } catch (OwnException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Fetching educational Fields" + e.getMessage());
        }
        return response;
    }



}