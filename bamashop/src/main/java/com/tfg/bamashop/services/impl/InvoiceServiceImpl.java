package com.tfg.bamashop.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.bamashop.dto.InvoiceDto;
import com.tfg.bamashop.dto.InvoiceRequestDto;
import com.tfg.bamashop.entities.Invoice;
import com.tfg.bamashop.entities.Post;
import com.tfg.bamashop.entities.User;
import com.tfg.bamashop.mappers.InvoiceMapper;
import com.tfg.bamashop.repositories.InvoiceRepository;
import com.tfg.bamashop.repositories.PostRepository;
import com.tfg.bamashop.repositories.UserRepository;
import com.tfg.bamashop.services.InvoiceService;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	InvoiceRepository invoiceRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PostRepository postRepository;

	@Override
	public List<InvoiceDto> getInvoices() {

		List<Invoice> invoices = invoiceRepository.findAll();

		if (invoices.size() < 0) {
			throw new RuntimeException("Invoices don't exist");
		}

		List<InvoiceDto> invoicesDto = new ArrayList<>();

		for (Invoice invoice : invoices) {
			Post post = postRepository.getById(invoice.getIdPost().getId());
			User user = userRepository.getById(invoice.getIdBuyer().getId());
			InvoiceDto invoiceDto = InvoiceMapper.INSTANCE.entityToDto(invoice, post, user);
			invoicesDto.add(invoiceDto);
		}

		return invoicesDto;
	}

	@Override
	public InvoiceDto getInvoice(Long id) {

		Invoice invoice = invoiceRepository.getById(id);

		if (invoice == null) {
			throw new RuntimeException("Invoice does not exist");
		}

		Post post = postRepository.getById(invoice.getIdPost().getId());
		User user = userRepository.getById(invoice.getIdBuyer().getId());

		InvoiceDto invoiceDto = InvoiceMapper.INSTANCE.entityToDto(invoice, post, user);
		return invoiceDto;
	}

	@Override
	public InvoiceDto saveInvoice(InvoiceRequestDto invoiceRequestDto) {

		if (invoiceRequestDto == null) {
			throw new RuntimeException("Invoice is missing data");
		}

		Invoice invoice = InvoiceMapper.INSTANCE.dtoToEntity(invoiceRequestDto);

		Invoice invoiceRet = invoiceRepository.save(invoice);

		Post post = postRepository.getById(invoiceRet.getIdPost().getId());
		User user = userRepository.getById(invoiceRet.getIdBuyer().getId());

		return InvoiceMapper.INSTANCE.entityToDto(invoiceRet, post, user);
	}

	@Override
	public InvoiceDto updateInvoice(Long id, InvoiceRequestDto invoiceRequestDto) {

		if (invoiceRequestDto == null) {
			throw new RuntimeException("Invoice is missing data");
		}

		Optional<Invoice> invoiceExists = invoiceRepository.findById(id);

		if (invoiceExists == null) {
			throw new RuntimeException("Invoice does not exist");
		}

		Invoice invoice = InvoiceMapper.INSTANCE.dtoToEntity(invoiceRequestDto);
		invoice.setId(id);

		Invoice invoiceRet = invoiceRepository.save(invoice);

		Post post = postRepository.getById(invoiceRet.getIdPost().getId());
		User user = userRepository.getById(invoiceRet.getIdBuyer().getId());

		return InvoiceMapper.INSTANCE.entityToDto(invoiceRet, post, user);
	}

	@Override
	public Invoice findById(Long id) {
		return invoiceRepository.getById(id);
	}

	@Override
	public void deleteInvoice(Long id) {
		invoiceRepository.deleteById(id);
	}

}
