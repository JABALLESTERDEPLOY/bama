package com.tfg.bamashop.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.bamashop.entities.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
