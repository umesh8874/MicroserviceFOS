package com.AN1D.an1d.Repository;
import com.AN1D.an1d.DTO.Order;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer>{
    
    @Query(value="SELECT COUNT(o.id) As total_orders FROM `order` o WHERE o.user_id = :user_id  AND o.is_referral = :is_referral AND o.transaction_status = :transaction_status", nativeQuery = true)
    int findByUserId(@Param("user_id") int user_id, @Param("is_referral") int is_referral, @Param("transaction_status") int transaction_status);

    @Query(value = "SELECT order_id FROM `order` ORDER BY id DESC LIMIT 1", nativeQuery = true)
    String findLastCreatedOrder();
}
