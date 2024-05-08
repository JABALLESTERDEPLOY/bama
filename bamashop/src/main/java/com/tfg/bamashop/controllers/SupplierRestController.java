package com.tfg.bamashop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.bamashop.dto.SupplierDto;
import com.tfg.bamashop.dto.SupplierRequestDto;
import com.tfg.bamashop.services.SupplierService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v0")
@CrossOrigin("*")
@Slf4j
@Tag(name = "SupplierRestController", description = "SupplierRestController API")
@SecurityRequirement(name = "bearerAuth")
public class SupplierRestController {

	@Autowired
	SupplierService supplierService;

	@Operation(summary = "Get Suppliers", operationId = "getSuppliers", description = "Get Suppliers")
	@GetMapping("/suppliers")
	public ResponseEntity<Object> getSuppliers() {

		log.info("Init getSuppliers - SupplierRestController");

		return ResponseEntity.ok(supplierService.getSuppliers());
	}

	@Operation(summary = "Get Supplier", operationId = "getSupplier", description = "Get a supplier")
	@GetMapping("/suppliers/{id}")
	public ResponseEntity<Object> getSupplier(@PathVariable Long id) {

		log.info("Init getSupplier - SupplierRestController");

		return ResponseEntity.ok(supplierService.getSupplier(id));
	}

	@Operation(summary = "Save Supplier", operationId = "saveSupplier", description = "Save a supplier")
	@PostMapping("/suppliers")
	public ResponseEntity<Object> saveSupplier(@RequestBody SupplierRequestDto supplierRequestDto) {

		log.info("Init saveSupplier - SupplierRestController");

		SupplierDto supplier = supplierService.saveSupplier(supplierRequestDto);

		return ResponseEntity.ok(supplier);
	}

	@Operation(summary = "Update Supplier", operationId = "updateSupplier", description = "Update a supplier")
	@PutMapping("/suppliers/{id}")
	public ResponseEntity<Object> updateSupplier(@PathVariable Long id,
			@RequestBody SupplierRequestDto supplierRequestDto) {

		log.info("Init updateUser - SupplierRestController");

		return ResponseEntity.ok(supplierService.updateSupplier(id, supplierRequestDto));
	}

	@Operation(summary = "Delete Supplier", operationId = "deleteSupplier", description = "Delete a supplier")
	@DeleteMapping("/suppliers/{id}")
	public ResponseEntity<Object> deleteSupplier(@PathVariable Long id) {

		log.info("Init deleteSupplier - SupplierRestController");

		supplierService.deleteSupplier(id);

		return ResponseEntity.ok("Supplier deleted");
	}
	
	@Operation(summary = "Get Supplier By CompanyName ", operationId = "getSupplierByCompanyName", description = "Get supplier by company name")
	@GetMapping("/suppliers/by-company-name/{companyName}")
	public ResponseEntity<Object> getSupplierByCompanyName(@PathVariable String companyName) {

		log.info("Init getSupplierByCompanyName - SupplierRestController");

		return ResponseEntity.ok(supplierService.findByCompanyName(companyName));
	}
}
