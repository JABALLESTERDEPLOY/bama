package com.tfg.bamashop.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "INVOICES")
@EntityListeners(AuditingEntityListener.class)
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_INVOICES", nullable = false)
	private Long id;

	@Column(name = "INVOICE_DATE", nullable = false)
	private LocalDate invoiceDate = LocalDate.now();

	@ManyToOne
	@JoinColumn(name = "ID_POST", nullable = false)
	private Post idPost;

	@ManyToOne
	@JoinColumn(name = "ID_BUYER", nullable = false)
	private User idBuyer;
}
