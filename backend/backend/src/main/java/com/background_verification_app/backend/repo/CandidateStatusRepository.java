package com.background_verification_app.backend.repo;

import com.background_verification_app.backend.dto.response.AllCandidateStatusDTO;
import com.background_verification_app.backend.entity.CandidateStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateStatusRepository extends JpaRepository<CandidateStatus, Integer> {
    boolean existsByCandidateId(Integer id);
    Optional<CandidateStatus> findByCandidateId(Integer id);

    @Query(value = "select u.first_name, u.email, c.status from users as u inner join candidate_status as c on u.id = c.candidate_id",
            nativeQuery = true)
    List<Object[]> fetchUserStatus();


}
