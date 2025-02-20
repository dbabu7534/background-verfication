package com.background_verification_app.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Data
public class AdminField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String field;
    private Boolean isActive;

    private String entity;

    public AdminField(String field, Boolean isActive, String entity) {
        this.field = field;
        this.isActive = isActive;
        this.entity = entity;
    }

    public AdminField() {
    }
}
