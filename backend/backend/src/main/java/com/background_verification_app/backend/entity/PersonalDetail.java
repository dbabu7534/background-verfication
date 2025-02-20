package com.background_verification_app.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
public class PersonalDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;
    private String idProof;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @JsonIgnore
    @OneToOne
    private User user;

    public PersonalDetail(String name, Integer age, String idProof, Date dateOfBirth) {
        this.name = name;
        this.age = age;
        this.idProof = idProof;
        this.dateOfBirth = dateOfBirth;
    }

    public PersonalDetail(){
    }

}
