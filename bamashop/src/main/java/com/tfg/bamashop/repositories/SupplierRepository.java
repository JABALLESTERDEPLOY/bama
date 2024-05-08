package com.tfg.bamashop.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tfg.bamashop.entities.Supplier;

@Repository
@Transactional
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

	@Query("SELECT s FROM Supplier s WHERE s.companyName LIKE %:companyName%")
	List<Supplier> findByCompanyName(String companyName);
}
