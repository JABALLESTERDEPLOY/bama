package com.tfg.bamashop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "ADDRESSES")
@EntityListeners(AuditingEntityListener.class)
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ADDRESSES", nullable = false)
	private Long id;

	@Column(name = "PHONE", nullable = false)
	private String phone;

	@Column(name = "NUMBER", nullable = false)
	private Long number;

	@Column(name = "STREET", nullable = false)
	private String street;

	@Column(name = "FLOOR", nullable = true)
	private String floor;

	@Column(name = "ZIPCODE", nullable = false)
	private String zipCode;

	@Column(name = "CITY", nullable = false)
	private String city;

	@Column(name = "PROVINCE", nullable = false)
	private String province;

	@Column(name = "COUNTRY", nullable = false)
	private String country;
}
