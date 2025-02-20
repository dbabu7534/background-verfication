package com.background_verification_app.backend.controller;

import com.background_verification_app.backend.dto.Response;
import com.background_verification_app.backend.service.impl.PageOrderService;
import com.background_verification_app.backend.repo.PageOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/admin")
public class PageOrderController {

    @Autowired
    private PageOrderService pageOrderService;

    @Autowired
    private PageOrderRepository pageOrderRepository;

    @PostMapping("/box-order")
    @PreAuthorize("hasAuthority('ADMIN')")
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
//    @PreAuthorize("hasAuthority('USER')")
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
}