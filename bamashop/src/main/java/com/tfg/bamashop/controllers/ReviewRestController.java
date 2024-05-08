package com.tfg.bamashop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.bamashop.dto.ReviewDto;
import com.tfg.bamashop.dto.ReviewRequestDto;
import com.tfg.bamashop.services.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v0")
@CrossOrigin("*")
@Slf4j
@Tag(name = "ReviewRestController", description = "ReviewRestController API")
@SecurityRequirement(name = "bearerAuth")
public class ReviewRestController {

	@Autowired
	ReviewService reviewService;

	@Operation(summary = "Get Reviews", operationId = "getReviews", description = "Get reviews")
	@GetMapping("/reviews")
	public ResponseEntity<Object> getReviews() {

		log.info("Init getReviews - ReviewRestController");

		return ResponseEntity.ok(reviewService.getReviews());
	}

	@Operation(summary = "Get Review", operationId = "getReview", description = "Get an review")
	@GetMapping("/reviews/{id}")
	public ResponseEntity<Object> getReview(@PathVariable Long id) {

		log.info("Init getReview - ReviewRestController");

		return ResponseEntity.ok(reviewService.getReview(id));
	}

	@Operation(summary = "Save Review", operationId = "saveReview", description = "Save an review")
	@PostMapping("/reviews")
	public ResponseEntity<Object> saveReview(@RequestBody ReviewRequestDto reviewRequestDto) {

		log.info("Init saveReview - ReviewRestController");

		ReviewDto reviewDto = reviewService.saveReview(reviewRequestDto);

		return ResponseEntity.ok(reviewDto);
	}

	@Operation(summary = "Update Review", operationId = "updateReview", description = "Update an review")
	@PutMapping("/reviews/{id}")
	public ResponseEntity<Object> updateReview(@PathVariable Long id,
			@RequestBody ReviewRequestDto reviewRequestDto) {

		log.info("Init updateReview - ReviewRestController");

		return ResponseEntity.ok(reviewService.updateReview(id, reviewRequestDto));
	}

	@Operation(summary = "Delete Review", operationId = "deleteReview", description = "Delete an review")
	@DeleteMapping("/reviews/{id}")
	public ResponseEntity<Object> deleteReview(@PathVariable Long id) {

		log.info("Init deleteReview - ReviewRestController");

		reviewService.deleteReview(id);

		return ResponseEntity.ok("Review deleted");
	}
}
