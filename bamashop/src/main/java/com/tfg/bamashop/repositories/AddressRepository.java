package com.tfg.bamashop.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.bamashop.entities.Address;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address, Long> {

	@Query("SELECT a FROM Address a WHERE " +
		       "LOWER(a.phone) LIKE LOWER(CONCAT('%', :anyField, '%')) OR " +
		       "LOWER(a.street) LIKE LOWER(CONCAT('%', :anyField, '%')) OR " +
		       "LOWER(a.floor) LIKE LOWER(CONCAT('%', :anyField, '%')) OR " +
		       "LOWER(a.zipCode) LIKE LOWER(CONCAT('%', :anyField, '%')) OR " +
		       "LOWER(a.city) LIKE LOWER(CONCAT('%', :anyField, '%')) OR " +
		       "LOWER(a.province) LIKE LOWER(CONCAT('%', :anyField, '%')) OR " +
		       "LOWER(a.country) LIKE LOWER(CONCAT('%', :anyField, '%'))")
	    List<Address> searchAllFields(String anyField);
}
