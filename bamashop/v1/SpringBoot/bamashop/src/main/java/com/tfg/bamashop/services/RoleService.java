package com.tfg.bamashop.services;

import java.util.List;

import com.tfg.bamashop.dto.RoleDto;
import com.tfg.bamashop.dto.RoleRequestDto;

public interface RoleService {

	RoleDto getByRoleName(String roleName);

	List<RoleDto> getRoles();
	
	RoleDto saveRole(RoleRequestDto roleRequestDto);
	
	RoleDto updateRole(String roleName, RoleRequestDto roleRequestDto);
	
	void deleteRole(String roleName);
}
