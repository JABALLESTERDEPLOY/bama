package com.tfg.bamashop.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.bamashop.entities.Role;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
    
    void deleteByRoleName(String roleName);
}
