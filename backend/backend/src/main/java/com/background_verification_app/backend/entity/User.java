package com.background_verification_app.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    private String firstName;
    private String lastName;

    @NotBlank(message = "Email is Required")
    @Column(unique = true)
    private String email;

    @JsonIgnore
    @NotBlank(message = "Password is required")
    private String password;

    @JsonIgnore
//    @Enumerated(EnumType.STRING)
    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_fk", referencedColumnName = "id")
    private PersonalDetail personalDetail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "educational_fk", referencedColumnName = "id")
    private EducationDetail educationDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "professional_fk", referencedColumnName = "id")
    private ProfessionalDetail professionalDetail;

    private Integer pageOrder;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FileEntity> files;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }


    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }


}
