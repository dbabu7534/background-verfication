package com.background_verification_app.backend.repo;

import com.background_verification_app.backend.dto.request.FieldStatusDTO;
import com.background_verification_app.backend.entity.AdminField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
//public interface AdminFieldRepository extends JpaRepository<AdminField, Integer> {
//    @Query(value = "select new com.background_verification_app.backend.dto.request.FieldStatusDTO(a.field, a.isActive) from AdminField a where e.entity = ?1")
//    List<FieldStatusDTO> findAllPersonalFields(String personalDetail);
//}

@Repository
public interface AdminFieldRepository extends JpaRepository<AdminField, Integer> {

    @Query("SELECT new com.background_verification_app.backend.dto.request.FieldStatusDTO(a.field, a.isActive) " +
            "FROM AdminField a WHERE a.entity = :entityName order by id")
    List<FieldStatusDTO> findAllFields(@Param("entityName") String entityName);



    @Query("SELECT a FROM AdminField a WHERE a.field = :field")
    Optional<AdminField> findByField(@Param("field") String field);

    @Query("SELECT new com.background_verification_app.backend.dto.request.FieldStatusDTO(a.field, a.isActive) " +
            "FROM AdminField a WHERE a.isActive = true ORDER BY a.id")
    List<FieldStatusDTO> getActiveFieldEntity();



}