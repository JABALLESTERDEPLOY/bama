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

import com.tfg.bamashop.dto.RoleRequestDto;
import com.tfg.bamashop.services.RoleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v0")
@CrossOrigin("*")
@Slf4j
@Tag(name = "RoleRestController", description = "RoleRestController API")
@SecurityRequirement(name = "bearerAuth")
public class RoleRestController {

	@Autowired
	RoleService roleService;

	@Operation(summary = "Get Roles", operationId = "getRoles", description = "Get roles")
	@GetMapping("/roles")
	public ResponseEntity<Object> getRoles() {
		try {
			log.info("Init getRoles - RoleRestController");
			return ResponseEntity.ok(roleService.getRoles());
		} catch (Exception ex) {
			return ResponseEntity.ok("Roles couldn't be shown");
		}
	}

	@Operation(summary = "Get Role", operationId = "getRole", description = "Get a role")
	@GetMapping("/roles/{roleName}")
	public ResponseEntity<Object> getRole(@PathVariable String roleName) {
		try {
			log.info("Init getRole - RoleRestController");
			return ResponseEntity.ok(roleService.findByRoleName(roleName));
		} catch (Exception ex) {
			return ResponseEntity.ok("Role couldn't be shown");
		}
	}

	@Operation(summary = "Save Role", operationId = "saveRole", description = "Save a role")
	@PostMapping("/roles")
	public ResponseEntity<Object> saveRole(@RequestBody RoleRequestDto roleRequestDto) {
		try {
			log.info("Init saveRole - RoleRestController");
			return ResponseEntity.ok(roleService.saveRole(roleRequestDto));
		} catch (Exception ex) {
			return ResponseEntity.ok("Role couldn't be created");
		}
	}

	@Operation(summary = "Update Role", operationId = "updateRole", description = "Update a role")
	@PutMapping("/roles/{roleName}")
	public ResponseEntity<Object> updateRole(@PathVariable String roleName,
			@RequestBody RoleRequestDto roleRequestDto) {
		try {
			log.info("Init updateRole - RoleRestController");
			return ResponseEntity.ok(roleService.updateRole(roleName, roleRequestDto));
		} catch (Exception ex) {
			return ResponseEntity.ok("Role couldn't be updated");
		}
	}

	@Operation(summary = "Delete Role", operationId = "deleteRole", description = "Delete a role")
	@DeleteMapping("/roles/{roleName}")
	public ResponseEntity<Object> deleteRole(@PathVariable String roleName) {
		try {
			log.info("Init deleteRole - RoleRestController");
			roleService.deleteRole(roleName);
			return ResponseEntity.ok("Role deleted");
		} catch (Exception ex) {
			return ResponseEntity.ok("Role couldn't be deleted");
		}
	}

	@Operation(summary = "Get Role By RoleName ", operationId = "getRoleByRoleName", description = "Get role by rolename")
	@GetMapping("/roles/by-rolename/{roleName}")
	public ResponseEntity<Object> getRoleByRoleName(@PathVariable String roleName) {

		log.info("Init getRoleByRoleName - RoleRestController");

		return ResponseEntity.ok(roleService.getByRoleName(roleName));
	}

}
