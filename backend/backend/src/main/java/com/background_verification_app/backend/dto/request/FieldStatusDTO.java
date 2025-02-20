package com.background_verification_app.backend.dto.request;

public class FieldStatusDTO {
    private String field;
    private Boolean isActive;


    public FieldStatusDTO(String field, Boolean isActive) {
        this.field = field;
        this.isActive = isActive;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
