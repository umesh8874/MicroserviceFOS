package com.AN1D.an1d.Repository;
import java.util.List;
import com.AN1D.an1d.DTO.UserReferral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReferralDao extends JpaRepository<UserReferral, Integer> {
    @Query(value="SELECT * FROM `user_referral` WHERE user_id = :user_id", nativeQuery = true)
    UserReferral findByUserId(@Param("user_id") int user_id);

    @Query(value = "SELECT * FROM `user_referral` WHERE user_id IN :user_ids ORDER BY created_at DESC", nativeQuery = true)
    List<UserReferral> findByUserIds(@Param("user_ids") List<Long> user_ids);

    @Query(value="SELECT * FROM `user_referral` WHERE user_referral_code = :referral_code", nativeQuery = true)
    UserReferral findByReferralCode(String referral_code);

    @Query(value = "SELECT * FROM `user_referral` WHERE `consumed_referral_code` = :consumed_referral_code", nativeQuery = true)
    UserReferral findByConsumedReferral(String consumed_referral_code);
}
