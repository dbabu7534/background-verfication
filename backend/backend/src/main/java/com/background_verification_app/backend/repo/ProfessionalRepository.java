package com.background_verification_app.backend.repo;

import com.background_verification_app.backend.entity.ProfessionalDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalRepository extends JpaRepository<ProfessionalDetail, Integer> {
}
