package com.tfg.bamashop.entities;

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
@Table(name = "IMAGES")
@EntityListeners(AuditingEntityListener.class)
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_IMAGES", nullable = false)
	private Long id;

	@Column(name = "URL", nullable = false)
	private String url;

	@ManyToOne
	@JoinColumn(name = "ID_PRODUCT", nullable = false)
	private Product idProduct;
}