package com.background_verification_app.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fileName;

    @Lob
    private byte[] data; // Store the PDF file as a byte array

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}