package com.tfg.bamashop.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tfg.bamashop.entities.Category;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long>  {
	
	@Query("SELECT c FROM Category c WHERE c.description LIKE %:description%")
	List<Category> searchByDescription(String description);
}
