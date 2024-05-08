package com.tfg.bamashop.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.tfg.bamashop.enums.ProductCondition;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "POSTS")
@EntityListeners(AuditingEntityListener.class)
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_POSTS", nullable = false)
	private Long id;

	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "PRICE", nullable = false)
	private Double price;

	@Column(name = "PRODUCT_CONDITION", nullable = false)
	@Enumerated(EnumType.STRING)
	private ProductCondition productCondition;

	@Column(name = "POSTED_DATE", nullable = false)
	private LocalDate postedDate;

	@ManyToMany
	@JoinTable(name = "POSTS_IMAGES", joinColumns = @JoinColumn(name = "ID_POSTS"), inverseJoinColumns = @JoinColumn(name = "ID_IMAGES"))
	private Set<Image> images;

	@ManyToOne
	@JoinColumn(name = "ID_CATEGORY", nullable = false)
	private Category idCategory;

	@ManyToOne
	@JoinColumn(name = "ID_SELLER", nullable = false)
	private User idSeller;

	@ManyToOne
	@JoinColumn(name = "ID_PRODUCT", nullable = true)
	private Product idProduct;

	@OneToMany(mappedBy = "idPost")
	private List<Invoice> invoices;

	@OneToMany(mappedBy = "idPost")
	private List<Review> reviews;
}
