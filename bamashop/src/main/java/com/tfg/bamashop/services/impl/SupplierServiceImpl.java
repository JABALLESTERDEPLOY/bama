package com.tfg.bamashop.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.bamashop.dto.SupplierDto;
import com.tfg.bamashop.dto.SupplierRequestDto;
import com.tfg.bamashop.entities.Address;
import com.tfg.bamashop.entities.Supplier;
import com.tfg.bamashop.mappers.SupplierMapper;
import com.tfg.bamashop.repositories.AddressRepository;
import com.tfg.bamashop.repositories.SupplierRepository;
import com.tfg.bamashop.services.SupplierService;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService{

	@Autowired
	SupplierRepository supplierRepository;
	
	@Autowired
	AddressRepository addressRepository;

	@Override
	public List<SupplierDto> getSuppliers() {

		List<Supplier> suppliers = supplierRepository.findAll();

		if (suppliers.size() < 0) {
			throw new RuntimeException("Supplier don't exist");
		}

		List<SupplierDto> suppliersDto = new ArrayList<>();

		for (Supplier supplier : suppliers) {
			Address address = addressRepository.getById(supplier.getAddress().getId());
			SupplierDto supplierDto = SupplierMapper.INSTANCE.entityToDto(supplier, address);
			suppliersDto.add(supplierDto);
		}

		return suppliersDto;
	}

	@Override
	public SupplierDto getSupplier(Long id) {

		Supplier supplier = supplierRepository.getById(id);

		if (supplier == null) {
			throw new RuntimeException("Supplier does not exist");
		}

		Address address = addressRepository.getById(supplier.getAddress().getId());

		SupplierDto supplierDto = SupplierMapper.INSTANCE.entityToDto(supplier, address);
		return supplierDto;
	}

	@Override
	public SupplierDto saveSupplier(SupplierRequestDto supplierRequestDto) {

		if (supplierRequestDto == null) {
			throw new RuntimeException("Supplier is missing data");
		}

		Supplier supplier = SupplierMapper.INSTANCE.dtoToEntity(supplierRequestDto);

		Supplier supplierRet = supplierRepository.save(supplier);

		Address address = addressRepository.getById(supplierRet.getAddress().getId());

		return SupplierMapper.INSTANCE.entityToDto(supplierRet, address);
	}

	@Override
	public SupplierDto updateSupplier(Long id, SupplierRequestDto supplierRequestDto) {

		if (supplierRequestDto == null) {
			throw new RuntimeException("Supplier is missing data");
		}

		Optional<Supplier> supplierExists = supplierRepository.findById(id);

		if (supplierExists == null) {
			throw new RuntimeException("Supplier does not exist");
		}

		Supplier supplier = SupplierMapper.INSTANCE.dtoToEntity(supplierRequestDto);
		supplier.setId(id);

		Supplier supplierRet = supplierRepository.save(supplier);

		Address address = addressRepository.getById(supplierRet.getAddress().getId());

		return SupplierMapper.INSTANCE.entityToDto(supplierRet, address);
	}

	@Override
	public Supplier findById(Long id) {

		return supplierRepository.getById(id);
	}
	
	@Override
	public List<SupplierDto> findByCompanyName(String companyName) {
		List<Supplier> suppliers = supplierRepository.findByCompanyName(companyName);
		List<SupplierDto> suppliersDto = new ArrayList<>();

		for (Supplier supplier : suppliers) {
			SupplierDto supplierDto = SupplierMapper.INSTANCE.entityToDto(supplier, supplier.getAddress());
			suppliersDto.add(supplierDto);
		}

		return suppliersDto;
	}
	
	@Override
	public void deleteSupplier(Long id) {
		supplierRepository.deleteById(id);
	}
}
