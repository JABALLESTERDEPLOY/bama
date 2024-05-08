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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "REVIEWS")
@EntityListeners(AuditingEntityListener.class)
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_REVIEWS", nullable = false)
	private Long id;

	@Column(name = "COMMENT", nullable = false)
	private String comment;

	@Column(name = "RATING", nullable = false)
	@Min(value = 0, message = "Rating must be at least 0")
	@Max(value = 5, message = "Rating must be at most 5")
	private Integer rating;

	@ManyToOne
	@JoinColumn(name = "ID_POST", nullable = false)
	private Post idPost;

	@ManyToOne
	@JoinColumn(name = "ID_USER", nullable = false)
	private User idUser;
}
