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

import com.tfg.bamashop.dto.UserDto;
import com.tfg.bamashop.dto.UserRequestDto;
import com.tfg.bamashop.services.RoleService;
import com.tfg.bamashop.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v0")
@CrossOrigin("*")
@Slf4j
@Tag(name = "UserRestController", description = "UserRestController API")
@SecurityRequirement(name = "bearerAuth")
public class UserRestController {

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Operation(summary = "Get Users", operationId = "getUsers", description = "Get users")
	@GetMapping("/users")
	public ResponseEntity<Object> getUsers() {

		log.info("Init getUsers - UserRestController");

		return ResponseEntity.ok(userService.getUsers());
	}

	@Operation(summary = "Get User", operationId = "getUser", description = "Get an user")
	@GetMapping("/users/{id}")
	public ResponseEntity<Object> getUser(@PathVariable Long id) {

		log.info("Init getUser - UserRestController");

		return ResponseEntity.ok(userService.getUser(id));
	}

	@Operation(summary = "Get User By Username ", operationId = "getUserByUsername", description = "Get users by username")
	@GetMapping("/users/by-username/{username}")
	public ResponseEntity<Object> getUserByUsername(@PathVariable String username) {

		log.info("Init getUserByUsername - UserRestController");

		return ResponseEntity.ok(userService.getUserByUsername(username));
	}

	@Operation(summary = "Save User", operationId = "saveUser", description = "Save an user")
	@PostMapping("/users")
	public ResponseEntity<Object> saveUser(@RequestBody UserRequestDto userRequestDto) {

		log.info("Init saveUser - UserRestController");

		UserDto user = userService.saveUser(userRequestDto);

		return ResponseEntity.ok(user);
	}

	@Operation(summary = "Update User", operationId = "updateUser", description = "Update an user")
	@PutMapping("/users/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {

		log.info("Init updateUser - UserRestController");

		return ResponseEntity.ok(userService.updateUser(id, userRequestDto));
	}

	@Operation(summary = "Delete User", operationId = "deleteUser", description = "Delete an user")
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Long id) {

		log.info("Init deleteUser - UserRestController");

		userService.deleteUser(id);

		return ResponseEntity.ok("User deleted");
	}
}
