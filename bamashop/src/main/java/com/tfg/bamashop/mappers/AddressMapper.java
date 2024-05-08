package com.tfg.bamashop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.tfg.bamashop.dto.AddressDto;
import com.tfg.bamashop.dto.AddressRequestDto;
import com.tfg.bamashop.entities.Address;

@Mapper
public interface AddressMapper {

	AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

	@Mapping(source = "address.id", target = "id")
	@Mapping(source = "address.phone", target = "phone")
	@Mapping(source = "address.number", target = "number")
	@Mapping(source = "address.street", target = "street")
	@Mapping(source = "address.floor", target = "floor")
	@Mapping(source = "address.zipCode", target = "zipCode")
	@Mapping(source = "address.city", target = "city")
	@Mapping(source = "address.province", target = "province")
	@Mapping(source = "address.country", target = "country")
	public AddressDto entityToDto(Address address);

	@Mapping(target = "id", ignore = true)
	@Mapping(source = "addressDto.phone", target = "phone")
	@Mapping(source = "addressDto.number", target = "number")
	@Mapping(source = "addressDto.street", target = "street")
	@Mapping(source = "addressDto.floor", target = "floor")
	@Mapping(source = "addressDto.zipCode", target = "zipCode")
	@Mapping(source = "addressDto.city", target = "city")
	@Mapping(source = "addressDto.province", target = "province")
	@Mapping(source = "addressDto.country", target = "country")
	public Address dtoToEntity(AddressRequestDto addressDto);

}
