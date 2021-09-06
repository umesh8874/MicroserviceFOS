package com.AN1D.an1d.Repository;
import java.util.List;
import com.AN1D.an1d.DTO.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
    
    @Query(value="SELECT *  FROM `product` where deleted = 0",nativeQuery = true)
	List<Product> findAllProducts(PageRequest pageRequest);

    @Query(value="SELECT p.*  FROM `product` p where p.barcode = :barcode AND p.deleted = 0",nativeQuery = true)
    Product findByBarcode(@Param("barcode") String barcode);
}
