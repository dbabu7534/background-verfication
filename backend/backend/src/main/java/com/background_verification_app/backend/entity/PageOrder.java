package com.background_verification_app.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "page_order")
@Data
public class PageOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer pageOrderId;
    private Integer pagePosition;
    private String pageName;

    @OneToOne
    private User user;

    public PageOrder() {}

    public PageOrder(Integer pageOrderId, Integer pagePosition, String pageName) {
        this.pageOrderId = pageOrderId;
        this.pagePosition = pagePosition;
        this.pageName = pageName;
    }

    // Getters and Setters
}
