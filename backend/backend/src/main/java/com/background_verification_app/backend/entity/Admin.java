//package com.background_verification_app.backend.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import java.util.Collection;
//import java.util.List;
//
//@Data
//@Entity
//@Table(name = "admins")
//public class Admin extends User{
//
//    @Id
//    @GeneratedValue( strategy = GenerationType.IDENTITY )
//    private Integer id;
//
//    public Admin(String firstName, String lastName, String email, String password) {
//        super(firstName, lastName, email, password);
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority("ADMIN"));
//    }
//
//
//}
