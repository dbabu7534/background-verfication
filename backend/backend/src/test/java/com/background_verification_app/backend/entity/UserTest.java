package com.background_verification_app.backend.entity;

import com.background_verification_app.backend.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser(){

    }
}