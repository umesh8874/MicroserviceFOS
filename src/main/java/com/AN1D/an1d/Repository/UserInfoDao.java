package com.AN1D.an1d.Repository;
import java.util.List;

import com.AN1D.an1d.DTO.UserInfo;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@Repository
public interface UserInfoDao extends JpaRepository<UserInfo, Integer> {
    
    @Query(value = "SELECT * FROM `user_info` WHERE user_id IN :user_ids AND deleted = 0 ORDER BY created_at DESC;", nativeQuery = true)
    // @Query(value="SELECT * FROM `signup` WHERE `mobile_number` LIKE :mobile_number ORDER BY updated_at DESC LIMIT 1", nativeQuery = true)
    List<UserInfo> findByIds(@Param("user_ids") List<Long> user_ids);

    @Query(value="SELECT * FROM `user_info` WHERE user_id = :user_id AND deleted = 0;", nativeQuery = true)
    UserInfo findById(@Param("user_id") int user_id);

    @Query(value="SELECT * FROM `user_info` WHERE email = :email AND deleted = 0;", nativeQuery = true)
    UserInfo findByEmail(@Param("email") String email);

    @Query(value="SELECT * FROM `user_info` WHERE mobile = :mobile AND deleted = 0;", nativeQuery = true)
    UserInfo findByMobile(@Param("mobile") String mobile);

    @Query(value="SELECT *  FROM `user_info` where deleted = 0",nativeQuery = true)
	List<UserInfo> findAll(PageRequest pageRequest);

    @Query(value="SELECT *  FROM `user_info` where deleted = 0",nativeQuery = true)
    List<UserInfo> findAllNonDeleted();

}
