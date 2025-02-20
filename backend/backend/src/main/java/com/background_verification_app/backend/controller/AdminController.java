package com.background_verification_app.backend.controller;

import com.background_verification_app.backend.dto.Response;
import com.background_verification_app.backend.dto.request.FieldStatusDTO;
import com.background_verification_app.backend.dto.request.UserStatusDTO;
import com.background_verification_app.backend.repo.PageOrderRepository;
import com.background_verification_app.backend.repo.UserRepository;
import com.background_verification_app.backend.service.impl.AdminService;
import com.background_verification_app.backend.service.impl.PageOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PageOrderService pageOrderService;

    @Autowired
    private PageOrderRepository pageOrderRepository;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/box-order")
    public ResponseEntity<Response> savePageOrder(@RequestBody Map<String, List<String>> requestBody) {

        List<String> pageNames = requestBody.get("boxOrders");

        if (pageNames == null || pageNames.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        Response response = pageOrderService.savePageOrder(pageNames);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    // Endpoint to get page order by page_order_id (GET request)
    @GetMapping("/{pageOrderId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<String>> getPageOrder(@PathVariable int pageOrderId) {
        try {
            // Fetch the page order based on the page_order_id
            List<String> pageOrderList = pageOrderRepository.findPageOrder(pageOrderId);

            // If the page order is found, return it
            if (pageOrderList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            return ResponseEntity.ok(pageOrderList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/personal")
    public ResponseEntity<Response> getPersonalFields(){
        Response response = adminService.getPersonalFields();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    @GetMapping("/professional")
    public ResponseEntity<Response> getProfessionalFields(){
        Response response = adminService.getProfessionalFields();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/educational")
    public ResponseEntity<Response> getEducationalFields(){
        Response response = adminService.getEducationalFields();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


//        @PutMapping("/update-personal")
//        public ResponseEntity<String> putNewPersonalFields(@RequestBody Map<String, List<AdminField>> requestBody) {
//            List<AdminField> updatedList = requestBody.get("personalField");
//
//            System.out.println(updatedList);
//
//            Response response = adminService.updatedPersonalFields(updatedList);
//
//            return ResponseEntity.status(response.getStatusCode()).body(response.getMessage());
//        }

    @PutMapping("/update-personal")
    public ResponseEntity<String> putNewPersonalFields(@Valid @RequestBody Map<String, List<FieldStatusDTO>> requestBody) {
        if (!requestBody.containsKey("personal_field")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("personalField is required");
        }
        List<FieldStatusDTO> updatedList = requestBody.get("personal_field");
        System.out.println("--->" + updatedList);
        Response response = adminService.updatedFields(updatedList);
        return ResponseEntity.status(response.getStatusCode()).body(response.getMessage());
    }

    @PutMapping("/update-professional")
    public ResponseEntity<String> putNewProfessionalFields(@Valid @RequestBody Map<String, List<FieldStatusDTO>> requestBody) {
        if (!requestBody.containsKey("professional_field")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Professional Field is required");
        }
        List<FieldStatusDTO> updatedList = requestBody.get("professional_field");

        Response response = adminService.updatedFields(updatedList);
        return ResponseEntity.status(response.getStatusCode()).body(response.getMessage());
    }


    @PutMapping("/update-educational")
    public ResponseEntity<String> putNewEducationalFields(@Valid @RequestBody Map<String, List<FieldStatusDTO>> requestBody) {
        if (!requestBody.containsKey("educational_field")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Educational Field is required");
        }
        List<FieldStatusDTO> updatedList = requestBody.get("educational_field");
        System.out.println("--->" + updatedList);
        Response response = adminService.updatedFields(updatedList);
        return ResponseEntity.status(response.getStatusCode()).body(response.getMessage());
    }


    @GetMapping("/all-users")
    public ResponseEntity<Response> user() {
        Response response = adminService.getAllUsers();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/user-status-update")
    public ResponseEntity<Response> status(@RequestBody UserStatusDTO user){
        Response response = adminService.userStatus(user);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/all-user-status")
    public Response allUserStatus(){
        Response response = adminService.getUserStatus();
        return ResponseEntity.status(response.getStatusCode()).body(response).getBody();
    }





}
