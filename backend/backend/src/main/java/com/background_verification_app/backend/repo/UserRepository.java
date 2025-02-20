package com.background_verification_app.backend.repo;

import com.background_verification_app.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Integer id);

    @Modifying
    @Query(value = "Select personal_fk from users where id = :id", nativeQuery = true)
    Integer findPersonalDetailFK(@Param("id") Integer id);


    @Modifying
    @Query(value = "update users set personal_fk = ?2 where id = ?1", nativeQuery = true)
    void setPersonalFK(Integer userId, Integer id);

    @Modifying
    @Query(value = "update users set educational_fk = ?2 where id = ?1", nativeQuery = true)
    void setEducationalFK(Integer userId, Integer id);


    @Modifying
    @Query(value = "update users set professional_fk = ?2 where id = ?1", nativeQuery = true)
    void setProfessionalFK(Integer userId, Integer id);


}
