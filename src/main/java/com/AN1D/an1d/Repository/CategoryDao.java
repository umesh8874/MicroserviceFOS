package com.AN1D.an1d.Repository;

import com.AN1D.an1d.DTO.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer>{

    @Query(value = "SELECT id FROM `category` WHERE name LIKE :category AND deleted = 0;", nativeQuery = true)
    int findByName(@Param("category") String category);
    
}