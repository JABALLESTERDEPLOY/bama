package com.tfg.bamashop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.tfg.bamashop.dto.SupplierDto;
import com.tfg.bamashop.dto.SupplierRequestDto;
import com.tfg.bamashop.entities.Address;
import com.tfg.bamashop.entities.Supplier;

@Mapper
public interface SupplierMapper {

	SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);

	@Mapping(source = "supplier.id", target = "id")
	@Mapping(source = "supplier.companyName", target = "companyName")
	@Mapping(source = "supplier.address.id", target = "idAddress")
	public SupplierDto entityToDto(Supplier supplier, Address address);

	@Mapping(target = "id", ignore = true)
	@Mapping(source = "companyName", target = "companyName")
	@Mapping(source = "idAddress", target = "address.id")
	@Mapping(target = "products", ignore = true)
	public Supplier dtoToEntity(SupplierRequestDto imageDto);

}
