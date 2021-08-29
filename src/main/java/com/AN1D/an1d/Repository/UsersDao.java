package com.AN1D.an1d.Repository;
import com.AN1D.an1d.DTO.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao extends JpaRepository<Users, Integer>{

    @Query(value="SELECT * FROM `users` WHERE access_token LIKE :access_token", nativeQuery = true)
    Users findByAccessToken(@Param("access_token") String access_token);

}