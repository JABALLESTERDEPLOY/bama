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

import com.tfg.bamashop.dto.ImageDto;
import com.tfg.bamashop.dto.ImageRequestDto;
import com.tfg.bamashop.services.ImageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v0")
@CrossOrigin("*")
@Slf4j
@Tag(name = "ImageRestController", description = "ImageRestController API")
@SecurityRequirement(name = "bearerAuth")
public class ImageRestController {

	@Autowired
	ImageService imageService;

	@Operation(summary = "Get Images", operationId = "getImages", description = "Get images")
	@GetMapping("/images")
	public ResponseEntity<Object> getImages() {

		log.info("Init getImages - ImageRestController");

		return ResponseEntity.ok(imageService.getImages());
	}

	@Operation(summary = "Get Image", operationId = "getImage", description = "Get an image")
	@GetMapping("/images/{id}")
	public ResponseEntity<Object> getImage(@PathVariable Long id) {

		log.info("Init getImage - ImageRestController");

		return ResponseEntity.ok(imageService.getImage(id));
	}

	@Operation(summary = "Save Image", operationId = "saveImage", description = "Save an image")
	@PostMapping("/images")
	public ResponseEntity<Object> saveImage(@RequestBody ImageRequestDto imageRequestDto) {

		log.info("Init saveImage - ImageRestController");

		ImageDto imageDto = imageService.saveImage(imageRequestDto);

		return ResponseEntity.ok(imageDto);
	}

	@Operation(summary = "Update Image", operationId = "updateImage", description = "Update an image")
	@PutMapping("/images/{id}")
	public ResponseEntity<Object> updateImage(@PathVariable Long id, @RequestBody ImageRequestDto imageRequestDto) {

		log.info("Init updateImage - ImageRestController");

		return ResponseEntity.ok(imageService.updateImage(id, imageRequestDto));
	}

	@Operation(summary = "Delete Image", operationId = "deleteImage", description = "Delete an image")
	@DeleteMapping("/images/{id}")
	public ResponseEntity<Object> deleteImage(@PathVariable Long id) {

		log.info("Init deleteImage - ImageRestController");

		imageService.deleteImage(id);

		return ResponseEntity.ok("Image deleted");
	}
}
