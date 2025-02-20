package com.background_verification_app.backend.repo;

import com.background_verification_app.backend.entity.EducationDetail;
import com.background_verification_app.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducationRepository extends JpaRepository<EducationDetail, Integer> {

}
