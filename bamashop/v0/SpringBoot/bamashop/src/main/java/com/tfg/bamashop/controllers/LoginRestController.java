package com.tfg.bamashop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.bamashop.dto.LoginDto;
import com.tfg.bamashop.entities.User;
import com.tfg.bamashop.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/auth")
@CrossOrigin("*")
@Slf4j
@Tag(name = "LoginRestController", description = "LoginRestController API")
public class LoginRestController {

	@Autowired
	private UserService userService;

	@Operation(summary = "Login user", operationId = "login", description = "Login an user")
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) {

		log.info("Init login - LoginRestController");

		User user = userService.getByUsernameAndPassword(loginDto);

		return ResponseEntity.ok(user);
	}
}
