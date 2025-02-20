package com.background_verification_app.backend.repo;

//import com.background_verification_app.backend.entity.PageOrder;
import com.background_verification_app.backend.entity.PageOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PageOrderRepository extends JpaRepository<PageOrder, Long> {

    @Query(value = "SELECT MAX(page_order_id) FROM public.page_order", nativeQuery = true)
    Optional<Integer> findMaxPageOrderId();

//    List<PageOrder> findByPageOrderIdOrderByPagePositionAsc(int pageOrderId);

    @Query(value = "SELECT page_name FROM page_order where page_order_id = ?1", nativeQuery = true)
    List<String> findPageOrder(int pageOrderId);


}
