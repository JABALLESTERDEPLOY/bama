package com.tfg.bamashop.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tfg.bamashop.entities.Role;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
	
    Role findByRoleName(String roleName);
    
	@Query("SELECT r FROM Role r WHERE r.roleName LIKE %:roleName%")
    List<Role> searchByRoleName(String roleName);
	
    void deleteByRoleName(String roleName);
}
