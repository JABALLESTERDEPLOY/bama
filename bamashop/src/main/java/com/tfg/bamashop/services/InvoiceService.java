package com.tfg.bamashop.services;

import java.util.List;

import com.tfg.bamashop.dto.InvoiceDto;
import com.tfg.bamashop.dto.InvoiceRequestDto;
import com.tfg.bamashop.entities.Invoice;

public interface InvoiceService {

	public List<InvoiceDto> getInvoices();

	public InvoiceDto getInvoice(Long id);

	public InvoiceDto saveInvoice(InvoiceRequestDto invoiceRequestDto);

	public InvoiceDto updateInvoice(Long id, InvoiceRequestDto invoiceRequestDto);

	public Invoice findById(Long id);

	public void deleteInvoice(Long id);
}
