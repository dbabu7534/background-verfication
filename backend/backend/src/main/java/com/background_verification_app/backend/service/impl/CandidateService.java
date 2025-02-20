package com.background_verification_app.backend.service.impl;

import com.background_verification_app.backend.dto.Response;
import com.background_verification_app.backend.dto.request.EducationalDetailDTO;
import com.background_verification_app.backend.dto.request.FieldStatusDTO;
import com.background_verification_app.backend.dto.request.PersonalDetailDTO;
import com.background_verification_app.backend.dto.request.ProfessionalDetailDTO;
import com.background_verification_app.backend.dto.response.AllFieldResponseDTO;
import com.background_verification_app.backend.entity.*;
import com.background_verification_app.backend.exception.OwnException;
import com.background_verification_app.backend.mapper.Mapper;
import com.background_verification_app.backend.mapper.MapperAllField;
import com.background_verification_app.backend.repo.*;
import com.background_verification_app.backend.service.interfac.ICandidateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class CandidateService implements ICandidateService {

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private PersonalRepository personalRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Autowired
    private AdminFieldRepository adminFieldRepository;

    @Autowired
    private FileRepository fileRepository;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Response savePersonalDetails(Integer id, PersonalDetailDTO personalDetailDTO) {
        Response response = new Response();

        try{
            User user = userRepository.findById(id)
                    .orElseThrow( () -> new UsernameNotFoundException( "User Not Found" ));

            PersonalDetail personalDetail = Mapper.mapToPersonal(personalDetailDTO);

            if(user.getPersonalDetail() != null){
                PersonalDetail existingDetails = user.getPersonalDetail();

                existingDetails.setName(personalDetail.getName());
                existingDetails.setDateOfBirth(personalDetail.getDateOfBirth());
                existingDetails.setAge(personalDetail.getAge());
                existingDetails.setIdProof(personalDetail.getIdProof());

                user.setPersonalDetail(existingDetails);
                userRepository.save(user);

                response.setStatusCode(200);
                response.setMessage("Personal Details Updated Successfully");
                return response;
            }

            PersonalDetail savedPersonalDetail = personalRepository.save(personalDetail);

            userRepository.setPersonalFK(user.getId(), savedPersonalDetail.getId());


            response.setStatusCode(200);
            response.setMessage("Successful Saved Personal Details");

        } catch (OwnException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error Savings a Personal Detail" + e.getMessage());
        }
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Response saveEducationDetails(Integer id, EducationalDetailDTO educationalDetailDTO) {
        Response response = new Response();

        try{
            User user = userRepository.findById(id).orElseThrow( ()-> new UsernameNotFoundException("User Not Found") );
            EducationDetail educationDetail = Mapper.mapToEducational(educationalDetailDTO);

            if(user.getEducationDetails() != null){
                EducationDetail existingDetails = user.getEducationDetails();

                existingDetails.setSslcSchoolName(educationDetail.getSslcSchoolName());
                existingDetails.setSslcPercentage(educationDetail.getSslcPercentage());

                existingDetails.setHslcSchoolName(educationDetail.getHslcSchoolName());
                existingDetails.setSslcPercentage(educationDetail.getSslcPercentage());

                existingDetails.setCollegeName(educationDetail.getCollegeName());
                existingDetails.setUniversityRollNumber(educationDetail.getUniversityRollNumber());
                existingDetails.setFinalCgpa(educationDetail.getFinalCgpa());

                user.setEducationDetails(existingDetails);
                userRepository.save(user);

                response.setStatusCode(200);
                response.setMessage("Educational Details Updated Successfully");
                return response;
            }

            EducationDetail savedEducationalDetail = educationRepository.save(educationDetail);

            userRepository.setEducationalFK(user.getId(), savedEducationalDetail.getId());


            response.setStatusCode(200);
            response.setMessage("Successful Saved Educational Details");

        } catch (OwnException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error Savings a User" + e.getMessage());
        }

        return response;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Response saveProfessionalDetails(Integer id, ProfessionalDetailDTO professionalDetailDTO) {
        Response response = new Response();

        try {
            User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

            System.out.println(personalRepository.findById(id));

            ProfessionalDetail professional = Mapper.mapToProfessional(professionalDetailDTO);

            if (user.getProfessionalDetail() != null) {
                ProfessionalDetail existingDetails = user.getProfessionalDetail();

                existingDetails.setCompanyName(professional.getCompanyName());
                existingDetails.setDateOfJoined(professional.getDateOfJoined());
                existingDetails.setPreviousCompanyRole(professional.getPreviousCompanyRole());
                existingDetails.setYearOfExperience(professional.getYearOfExperience());

                user.setProfessionalDetail(existingDetails);
                userRepository.save(user);

                response.setStatusCode(200);
                response.setMessage("Already Saved the Professional Details");
                return response;
            }

            ProfessionalDetail savedProfessionalDetail = professionalRepository.save(professional);

            userRepository.setProfessionalFK(user.getId(), savedProfessionalDetail.getId());


            response.setStatusCode(200);
            response.setMessage("Successful Saved Personal Details");

        } catch (OwnException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Savings a User" + e.getMessage());
        }

        return response;
    }



    @Override
    public Response getUsersUploadDetails(Integer id) {
        Response response = new Response();
        try {
            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));


            Map<String, Object> responseMap = new HashMap<>();


            // ✅ Personal Details as a Map
            List<FieldStatusDTO> personalField = adminFieldRepository.findAllFields("personal_detail");
            Map<String, String> personalFieldMap = new LinkedHashMap<>();
            PersonalDetail personalDetail = existingUser.getPersonalDetail();

            Map<String, Object> personalMapping = new LinkedHashMap<>();


            personalMapping.put("name", Optional.ofNullable(personalDetail)
                    .map( p -> Optional.ofNullable(p.getName()).orElse(null) )
                    .orElse(null) );

            personalMapping.put("age", Optional.ofNullable(personalDetail)
                    .map(p -> Optional.ofNullable(p.getAge()).orElse(0) )
                    .orElse(null) );

            personalMapping.put("date_of_birth", Optional.ofNullable(personalDetail)
                    .map(p -> Optional.ofNullable(p.getDateOfBirth()) .orElse(null) )
                    .orElse(null));

            personalMapping.put("id_proof", Optional.ofNullable(personalDetail)
                    .map(p -> Optional.ofNullable(p.getIdProof()).orElse(null) )
                    .orElse(null));


            for (FieldStatusDTO dto : personalField) {
                if (dto.getIsActive() && personalMapping.containsKey(dto.getField())) {
                    personalFieldMap.put(dto.getField(), String.valueOf(personalMapping.get(dto.getField())));
                }
            }
            responseMap.put("personal_details", personalFieldMap);

            // ✅ Professional Details as a Map
            List<FieldStatusDTO> professionalField = adminFieldRepository.findAllFields("professional_detail");
            Map<String, String> professionalFieldMap = new LinkedHashMap<>();
            ProfessionalDetail professionalDetail = existingUser.getProfessionalDetail();

            Map<String, Object> professionalMapping = new HashMap<>();

//            if(professionalDetail.getCompanyName())

            professionalMapping.put("company_name",  Optional.ofNullable(professionalDetail)
                    .map(ProfessionalDetail::getCompanyName)
                    .orElse(null));

            professionalMapping.put("previous_company_role", Optional.ofNullable(professionalDetail)
                    .map( p -> Optional.ofNullable(p.getPreviousCompanyRole()).orElse(null) )
                    .orElse(null));

            professionalMapping.put("year_of_experience", Optional.ofNullable(professionalDetail)
                    .map( p-> Optional.ofNullable(p.getYearOfExperience()). orElse(0) )
                    .orElse(0));

            professionalMapping.put("dateOfJoined", Optional.ofNullable(professionalDetail)
                    .map( p -> Optional.ofNullable(p.getDateOfJoined()). orElse(null) ) // Convert Date to String
                    .orElse(null));


            for (FieldStatusDTO dto : professionalField) {
                if (dto.getIsActive() && professionalMapping.containsKey(dto.getField())) {
                    professionalFieldMap.put(dto.getField(), String.valueOf(professionalMapping.get(dto.getField())));
                }
            }
            responseMap.put("professional_details", professionalFieldMap);

            // ✅ Educational Details as a Single Map (instead of a list)
            List<FieldStatusDTO> educationalField = adminFieldRepository.findAllFields("educational_detail");
            Map<String, String> educationalFieldMap = new LinkedHashMap<>();
            EducationDetail educationDetail = existingUser.getEducationDetails();

            Map<String, Object> educationalMapping = new HashMap<>();
            educationalMapping.put("sslc_school_name", Optional.ofNullable(educationDetail)
                    .map( e -> Optional.ofNullable(e.getSslcSchoolName()).orElse(null) )
                    .orElse(null));

            educationalMapping.put("sslc_percentage", Optional.ofNullable(educationDetail)
                    .map( e -> Optional.ofNullable( e.getSslcPercentage() ) .orElse(0.0) )
                    .orElse(null));

            educationalMapping.put("hslc_school_name", Optional.ofNullable(educationDetail)
                    .map(e -> Optional.ofNullable(e.getHslcSchoolName()).orElse(null))
                    .orElse(null));

            educationalMapping.put("hslc_percentage", Optional.ofNullable(educationDetail)
                    .map( e -> Optional.ofNullable(e.getHslcPercentage()).orElse(0.0) )
                    .orElse(null) );

            educationalMapping.put("collegeName", Optional.ofNullable(educationDetail)
                    .map(e -> Optional.ofNullable( e.getCollegeName() ).orElse(null) )
                    .orElse(null) );

            educationalMapping.put("university_roll_number", Optional.ofNullable(educationDetail)
                    .map( e -> Optional.ofNullable( e.getUniversityRollNumber() ) .orElse(null) )
                    .orElse(null) );


            educationalMapping.put("final_cgpa", Optional.ofNullable(educationDetail)
                    .map( e -> Optional.ofNullable(e.getFinalCgpa() ).orElse(0.0) )
                    .orElse(null) );


            for (FieldStatusDTO dto : educationalField) {
                if (dto.getIsActive() && educationalMapping.containsKey(dto.getField())) {
                    educationalFieldMap.put(dto.getField(), String.valueOf(educationalMapping.get(dto.getField())));
                }
            }
            responseMap.put("educational_details", educationalFieldMap); // ✅ Now a Map instead of a List


            // ✅ Set response correctly
            response.setUploadedDocuments(responseMap);
            response.setStatusCode(200);
            response.setMessage( existingUser.getFirstName() +  " Your Uploaded Details " );


        } catch (OwnException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Getting Details are Error " + e.getMessage());
        }

        return response;

    }


//    private static final String STORAGE_DIRECTORY = "E:\\Candidate Documents";
//
//    public void saveFile(MultipartFile file) throws IOException {
//        if(file == null){
//            throw new NullPointerException("Your File is Null Please Check Correct File");
//        }
//        var targetFile = new File(STORAGE_DIRECTORY + File.separator + file.getOriginalFilename()) ;
//
//        Files.copy(file.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//    }
//
//    public File getDownloadFile(String fileName) throws FileNotFoundException {
//        if(fileName != null){
//            throw new NullPointerException("File Name is Null");
//        }
//        var fileToDownload = new File(STORAGE_DIRECTORY + File.separator + fileName);
//
//        if(!fileToDownload.exists()){
//            throw new FileNotFoundException("No File Named :" + fileName);
//        }
//        return fileToDownload;
//    }

//    public Response getActiveFields() {
//        Response response = new Response();
//        try {
//            List<FieldStatusDTO> activeFieldEntity = adminFieldRepository.getActiveFieldEntity();
//            response.setActiveFields(activeFieldEntity);
//
//            response.setStatusCode(200); // Success status code
//            response.setMessage("Active fields fetched successfully");
//        } catch(OwnException e){
//            response.setStatusCode(404);
//            response.setMessage(e.getMessage());
//        } catch(Exception e){
//            response.setStatusCode(500);
//            response.setMessage("Getting Details are Error " + e.getMessage());
//        }
//
//        return response;


    public Response uploadFile(Integer userId, MultipartFile file ) throws IOException {

        Response response = new Response();

        try{
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            FileEntity fileEntity = new FileEntity();
            fileEntity.setFileName(file.getOriginalFilename());
            fileEntity.setData(file.getBytes());

            fileEntity.setUser(user); // Associate the file with the user
            fileRepository.save(fileEntity);


            response.setStatusCode(200);
            response.setMessage( user.getFirstName() +  " Your Uploaded Details " );


        }
        catch (OwnException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Getting Details are Error " + e.getMessage());
        }

        return response;
    }


    public FileEntity downloadFile(Integer id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }

    public List<FileEntity> getFilesByUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFiles();
    }




}
