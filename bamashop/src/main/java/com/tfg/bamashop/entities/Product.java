package com.tfg.bamashop.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "PRODUCTS")
@EntityListeners(AuditingEntityListener.class)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PRODUCTS", nullable = false)
	private Long id;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@ManyToOne
	@JoinColumn(name = "ID_SUPPLIER", nullable = false)
	private Supplier idSupplier;

	@Column(name = "PRICE", nullable = false)
	private Double price;

	@Column(name = "STOCK", nullable = false)
	private Integer stock;

	@ManyToOne
	@JoinColumn(name = "ID_CATEGORY", nullable = false)
	private Category idCategory;

	@OneToMany(mappedBy = "idProduct")
	private List<Image> images;

	@OneToMany(mappedBy = "idProduct")
	private List<Post> posts;

	@Column(name = "STATUS")
	private Boolean status = true;
}