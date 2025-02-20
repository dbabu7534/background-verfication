package com.background_verification_app.backend.repo;

import com.background_verification_app.backend.entity.PersonalDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface PersonalRepository extends JpaRepository<PersonalDetail, Integer> {

    @Modifying
    @Query(
            value = "update personal_detail set name = ?2, age = ?3, id_proof = ?4, date = ?5  where id = ?1",
            nativeQuery = true
    )
    void updatePersonalDetailsById(Integer id, String name, Integer age, String idProof, Date date);





}