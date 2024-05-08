package com.tfg.bamashop.services;

import java.util.List;

import com.tfg.bamashop.dto.SupplierDto;
import com.tfg.bamashop.dto.SupplierRequestDto;
import com.tfg.bamashop.entities.Supplier;

public interface SupplierService {

	public List<SupplierDto> getSuppliers();
	
	public SupplierDto getSupplier(Long id);

	public SupplierDto saveSupplier(SupplierRequestDto supplierRequestDto);
	
	public SupplierDto updateSupplier(Long id, SupplierRequestDto supplierRequestDto);
	
	public Supplier findById(Long id);
	
	public List<SupplierDto> findByCompanyName(String companyName);

	void deleteSupplier(Long id);
}
