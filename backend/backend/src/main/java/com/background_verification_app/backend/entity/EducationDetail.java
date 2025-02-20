package com.background_verification_app.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
public class EducationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String sslcSchoolName;
    private double sslcPercentage;

    private String hslcSchoolName;
    private double hslcPercentage;

    private String collegeName;
    private String universityRollNumber;
    private double finalCgpa;

    @JsonIgnore
    @OneToOne
    private User user;

    public EducationDetail(String sslcSchoolName, double sslcPercentage, String hslcSchoolName, double hslcPercentage, String collegeName, String universityRollNumber, double finalCgpa) {
        this.sslcSchoolName = sslcSchoolName;
        this.sslcPercentage = sslcPercentage;
        this.hslcSchoolName = hslcSchoolName;
        this.hslcPercentage = hslcPercentage;
        this.collegeName = collegeName;
        this.universityRollNumber = universityRollNumber;
        this.finalCgpa = finalCgpa;
    }

    public EducationDetail() {
    }
}
