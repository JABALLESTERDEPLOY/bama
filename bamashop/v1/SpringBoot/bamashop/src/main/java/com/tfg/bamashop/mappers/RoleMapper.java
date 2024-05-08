package com.tfg.bamashop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.tfg.bamashop.dto.RoleDto;
import com.tfg.bamashop.dto.RoleRequestDto;
import com.tfg.bamashop.entities.Role;

@Mapper
public interface RoleMapper {

	RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

	@Mapping(source = "role.id", target = "id")
	@Mapping(source = "role.roleName", target = "roleName")
	public RoleDto entityToDto(Role role);

	@Mapping(target = "id", ignore = true)
	@Mapping(source = "roleDto.roleName", target = "roleName")
	public Role dtoToEntity(RoleRequestDto roleDto);
}
