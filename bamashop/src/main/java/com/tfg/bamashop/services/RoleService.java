package com.tfg.bamashop.services;

import java.util.List;

import com.tfg.bamashop.dto.RoleDto;
import com.tfg.bamashop.dto.RoleRequestDto;
import com.tfg.bamashop.entities.Role;

public interface RoleService {

	RoleDto findByRoleName(String roleName);

	List<RoleDto> getRoles();
	
	RoleDto saveRole(RoleRequestDto roleRequestDto);
	
	RoleDto updateRole(String roleName, RoleRequestDto roleRequestDto);
	
	public List<Role> getByRoleName(String roleName);
	
	void deleteRole(String roleName);
}
