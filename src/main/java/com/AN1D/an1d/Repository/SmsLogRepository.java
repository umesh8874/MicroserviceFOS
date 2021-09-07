package com.AN1D.an1d.Repository;
import com.AN1D.an1d.DTO.SmsLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsLogRepository extends JpaRepository<SmsLog, Integer>{
    
}
