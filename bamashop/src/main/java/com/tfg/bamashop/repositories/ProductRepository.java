package com.tfg.bamashop.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tfg.bamashop.entities.Product;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Modifying
	@Query("UPDATE Product p SET p.status = :active WHERE p.id = :id")
	public void updateStatusById(@Param("active") Boolean active, @Param("id") Long id);
	
	@Query("SELECT p FROM Product p WHERE p.description LIKE %:description%")
	List<Product> searchByDescription(String description);
}
