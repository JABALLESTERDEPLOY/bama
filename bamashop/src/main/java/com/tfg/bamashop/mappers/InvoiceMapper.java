package com.tfg.bamashop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.tfg.bamashop.dto.InvoiceDto;
import com.tfg.bamashop.dto.InvoiceRequestDto;
import com.tfg.bamashop.entities.Invoice;
import com.tfg.bamashop.entities.Post;
import com.tfg.bamashop.entities.User;

@Mapper
public interface InvoiceMapper {

	InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

	@Mapping(source = "invoice.id", target = "id")
	@Mapping(source = "invoice.invoiceDate", target = "invoiceDate")
	@Mapping(source = "post.id", target = "idPost")
	@Mapping(source = "buyer.id", target = "idBuyer")
	public InvoiceDto entityToDto(Invoice invoice, Post post, User buyer);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "invoiceDate", ignore = true)
	@Mapping(source = "invoiceDto.idPost", target = "idPost.id")
	@Mapping(source = "invoiceDto.idBuyer", target = "idBuyer.id")
	public Invoice dtoToEntity(InvoiceRequestDto invoiceDto);
}
