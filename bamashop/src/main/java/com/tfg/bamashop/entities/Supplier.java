package com.tfg.bamashop.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "SUPPLIERS")
@EntityListeners(AuditingEntityListener.class)
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_SUPPLIERS", nullable = false)
	private Long id;

	@Column(name = "COMPANY_NAME", nullable = false)
	private String companyName;

	@OneToMany(mappedBy = "idCategory")
	private List<Product> products;

	@OneToOne
	@JoinColumn(name = "id_address")
	private Address address;
}
