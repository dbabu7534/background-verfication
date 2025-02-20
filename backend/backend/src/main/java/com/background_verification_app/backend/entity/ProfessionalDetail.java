package com.background_verification_app.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class ProfessionalDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String companyName;
    private String previousCompanyRole;
    private Integer yearOfExperience;
    @Temporal(TemporalType.DATE)
    private Date dateOfJoined;

    @JsonIgnore
    @OneToOne
    private User user;

    public ProfessionalDetail(String companyName, String previousCompanyRole, Integer yearOfExperience, Date dateOfJoined) {
        this.companyName = companyName;
        this.previousCompanyRole = previousCompanyRole;
        this.yearOfExperience = yearOfExperience;
        this.dateOfJoined = dateOfJoined;
    }

    public ProfessionalDetail() {
    }
}
