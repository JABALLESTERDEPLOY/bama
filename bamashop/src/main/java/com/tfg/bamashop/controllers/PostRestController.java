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

import com.tfg.bamashop.dto.PostDto;
import com.tfg.bamashop.dto.PostRequestDto;
import com.tfg.bamashop.services.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v0")
@CrossOrigin("*")
@Slf4j
@Tag(name = "PostRestController", description = "PostRestController API")
@SecurityRequirement(name = "bearerAuth")
public class PostRestController {

	@Autowired
	PostService postService;

	@Operation(summary = "Get Posts", operationId = "getPosts", description = "Get Posts")
	@GetMapping("/posts")
	public ResponseEntity<Object> getPosts() {

		log.info("Init getPosts - PostRestController");

		return ResponseEntity.ok(postService.getPosts());
	}

	@Operation(summary = "Get Post", operationId = "getPost", description = "Get a post")
	@GetMapping("/posts/{id}")
	public ResponseEntity<Object> getPost(@PathVariable Long id) {

		log.info("Init getPost - PostRestController");

		return ResponseEntity.ok(postService.getPost(id));
	}

	@Operation(summary = "Save Post", operationId = "savePost", description = "Save a post")
	@PostMapping("/posts")
	public ResponseEntity<Object> savePost(@RequestBody PostRequestDto postRequestDto) {

		log.info("Init savePost - PostRestController");

		PostDto post = postService.savePost(postRequestDto);

		return ResponseEntity.ok(post);
	}

	@Operation(summary = "Update Post", operationId = "updatePost", description = "Update a post")
	@PutMapping("/posts/{id}")
	public ResponseEntity<Object> updatePost(@PathVariable Long id,
			@RequestBody PostRequestDto postRequestDto) {

		log.info("Init updateUser - PostRestController");

		return ResponseEntity.ok(postService.updatePost(id, postRequestDto));
	}

	@Operation(summary = "Delete Post", operationId = "deletePost", description = "Delete a post")
	@DeleteMapping("/posts/{id}")
	public ResponseEntity<Object> deletePost(@PathVariable Long id) {

		log.info("Init deletePost - PostRestController");

		postService.deletePost(id);

		return ResponseEntity.ok("Post deleted");
	}
}
