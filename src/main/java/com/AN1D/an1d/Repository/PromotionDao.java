package com.AN1D.an1d.Repository;
import java.util.List;

import com.AN1D.an1d.DTO.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionDao extends JpaRepository<Promotion, Integer>{

    @Query(value="SELECT * FROM `promotion` WHERE promotion_name LIKE :promotion_name AND deleted = 0", nativeQuery = true)
    List<Promotion> findByPromoName(@Param("promotion_name") String promotionName);

    @Query(value="SELECT * FROM `promotion` WHERE unique_url LIKE :unique_url AND deleted = 0", nativeQuery = true)
    List<Promotion> findByPromoUniqueUrl(@Param("unique_url") String uniqueUrl);

    
    @Query(value="SELECT * FROM `promotion` WHERE status = 0 AND deleted = 0 ORDER BY created_at DESC", nativeQuery = true)
    List<Promotion> findByAllActivePromotions();
    
}