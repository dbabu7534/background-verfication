package com.background_verification_app.backend.controller;

import com.background_verification_app.backend.dto.Response;
import com.background_verification_app.backend.dto.request.EducationalDetailDTO;
import com.background_verification_app.backend.dto.request.PersonalDetailDTO;
import com.background_verification_app.backend.dto.request.ProfessionalDetailDTO;
import com.background_verification_app.backend.entity.FileEntity;
import com.background_verification_app.backend.service.impl.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private CandidateService candidateService;

    @PostMapping("/educational/{id}")
//    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Response> saveEducational(@PathVariable("id") Integer id, @RequestBody EducationalDetailDTO educationalDetail){
        Response response = candidateService.saveEducationDetails(id, educationalDetail);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/personal/{id}")
//    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Response> savePersonal(@PathVariable("id") Integer id, @RequestBody PersonalDetailDTO personalDetailDTO){
        Response response = candidateService.savePersonalDetails(id, personalDetailDTO);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    @PostMapping("/professional/{id}")
//    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Response> saveProfessional(@PathVariable("id") Integer id, @RequestBody ProfessionalDetailDTO professionalDetailDTO){
        Response response = candidateService.saveProfessionalDetails(id, professionalDetailDTO);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    @GetMapping("/user-detail/{id}")
//    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Response> getById(@PathVariable("id") Integer id ){
        Response response = candidateService.getUsersUploadDetails(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/upload-file/{id}")
//    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Response> uploadFile(@PathVariable("id") Integer id,
                                               @RequestParam("file") MultipartFile file) throws IOException {
        Response response = candidateService.uploadFile( id, file);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }



//    @GetMapping("/download")
//    public ResponseEntity<Resource> downloadFile(@RequestParam("file") String fileName) throws FileNotFoundException {
//        try {
//            var fileToDownload = candidateService.getDownloadFile(fileName);
//            return ResponseEntity.ok()
//                    .contentLength(fileToDownload.length())
//                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                    .body(new InputStreamResource(Files.newInputStream(fileToDownload.toPath())));
//        } catch (Exception e){
//            return ResponseEntity.notFound().build();
//        }
//
//    }




}
