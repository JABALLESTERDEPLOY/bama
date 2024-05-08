package com.tfg.bamashop.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tfg.bamashop.entities.Address;
import com.tfg.bamashop.entities.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.username LIKE %:username%")
	List<User> getUserByUsername(String username);

	User findByUsername(String username);

	User findByAddress(Address address);
}
