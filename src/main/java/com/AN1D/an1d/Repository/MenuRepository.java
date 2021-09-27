package com.AN1D.an1d.Repository;
import com.AN1D.an1d.DTO.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {


}
