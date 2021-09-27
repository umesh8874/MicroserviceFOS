package com.AN1D.an1d.Repository;
import java.util.List;
import com.AN1D.an1d.DTO.PromotionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionDetailsDao extends JpaRepository<PromotionDetail, Integer>{
    
    @Query(value="SELECT *  FROM `promotion_detail` WHERE `promotion_id` = :promotion_id AND `deleted` = 0 ORDER BY created_at DESC;", nativeQuery = true)
    List<PromotionDetail> findByPromotionId(@Param("promotion_id") String promotion_id);
}