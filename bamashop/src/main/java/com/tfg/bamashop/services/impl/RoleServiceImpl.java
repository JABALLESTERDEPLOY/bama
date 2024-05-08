package com.tfg.bamashop.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.bamashop.dto.RoleDto;
import com.tfg.bamashop.dto.RoleRequestDto;
import com.tfg.bamashop.entities.Role;
import com.tfg.bamashop.mappers.RoleMapper;
import com.tfg.bamashop.repositories.RoleRepository;
import com.tfg.bamashop.services.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public RoleDto findByRoleName(String roleName) {
		Role role = roleRepository.findByRoleName(roleName);
		if (role == null) {
			throw new RuntimeException("Role does not exist");
		}
		RoleDto roleDto = RoleMapper.INSTANCE.entityToDto(role);
		return roleDto;
	}

	@Override
	public List<RoleDto> getRoles() {
		List<Role> roles = roleRepository.findAll();
		List<RoleDto> rolesDto = new ArrayList<>();

		for (Role role : roles) {
			RoleDto roleDto = RoleMapper.INSTANCE.entityToDto(role);
			rolesDto.add(roleDto);
		}

		return rolesDto;
	}

	@Override
	public RoleDto saveRole(RoleRequestDto roleRequestDto) {
		Role role = RoleMapper.INSTANCE.dtoToEntity(roleRequestDto);
		Role roleRet = roleRepository.save(role);

		return RoleMapper.INSTANCE.entityToDto(roleRet);
	}

	@Override
	public RoleDto updateRole(String roleName, RoleRequestDto roleRequestDto) {
		if (roleName.equals(roleRequestDto.getRoleName()) && roleRequestDto.getRoleName().isEmpty()) {
			throw new RuntimeException("Role exists or is empty.");
		}

		Role roleExist = roleRepository.findByRoleName(roleName);

		if (roleExist == null) {
			throw new RuntimeException("Role does not exist");
		}

		Role role = RoleMapper.INSTANCE.dtoToEntity(roleRequestDto);

		role.setId(roleExist.getId());

		Role roleRet = roleRepository.save(role);

		return RoleMapper.INSTANCE.entityToDto(roleRet);
	}

	@Override
	public List<Role> getByRoleName(String roleName) {
		return roleRepository.searchByRoleName(roleName);
	}
	
	@Override
	public void deleteRole(String roleName) {
		roleRepository.deleteByRoleName(roleName);
	}

}
