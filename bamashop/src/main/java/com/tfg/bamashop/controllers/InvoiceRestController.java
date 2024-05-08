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

import com.tfg.bamashop.dto.InvoiceDto;
import com.tfg.bamashop.dto.InvoiceRequestDto;
import com.tfg.bamashop.services.InvoiceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v0")
@CrossOrigin("*")
@Slf4j
@Tag(name = "InvoiceRestController", description = "InvoiceRestController API")
@SecurityRequirement(name = "bearerAuth")
public class InvoiceRestController {

	@Autowired
	InvoiceService invoiceService;

	@Operation(summary = "Get Invoices", operationId = "getInvoices", description = "Get invoices")
	@GetMapping("/invoices")
	public ResponseEntity<Object> getInvoices() {

		log.info("Init getInvoices - InvoiceRestController");

		return ResponseEntity.ok(invoiceService.getInvoices());
	}

	@Operation(summary = "Get Invoice", operationId = "getInvoice", description = "Get an invoice")
	@GetMapping("/invoices/{id}")
	public ResponseEntity<Object> getInvoice(@PathVariable Long id) {

		log.info("Init getInvoice - InvoiceRestController");

		return ResponseEntity.ok(invoiceService.getInvoice(id));
	}

	@Operation(summary = "Save Invoice", operationId = "saveInvoice", description = "Save an invoice")
	@PostMapping("/invoices")
	public ResponseEntity<Object> saveInvoice(@RequestBody InvoiceRequestDto invoiceRequestDto) {

		log.info("Init saveInvoice - InvoiceRestController");

		InvoiceDto invoiceDto = invoiceService.saveInvoice(invoiceRequestDto);

		return ResponseEntity.ok(invoiceDto);
	}

	@Operation(summary = "Update Invoice", operationId = "updateInvoice", description = "Update an invoice")
	@PutMapping("/invoices/{id}")
	public ResponseEntity<Object> updateInvoice(@PathVariable Long id,
			@RequestBody InvoiceRequestDto invoiceRequestDto) {

		log.info("Init updateInvoice - InvoiceRestController");

		return ResponseEntity.ok(invoiceService.updateInvoice(id, invoiceRequestDto));
	}

	@Operation(summary = "Delete Invoice", operationId = "deleteInvoice", description = "Delete an invoice")
	@DeleteMapping("/invoices/{id}")
	public ResponseEntity<Object> deleteInvoice(@PathVariable Long id) {

		log.info("Init deleteInvoice - InvoiceRestController");

		invoiceService.deleteInvoice(id);

		return ResponseEntity.ok("Invoice deleted");
	}
}
