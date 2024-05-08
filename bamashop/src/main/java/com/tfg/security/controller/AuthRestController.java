package com.tfg.security.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.bamashop.dto.UserDto;
import com.tfg.bamashop.dto.UserRegisterRequestDto;
import com.tfg.bamashop.entities.User;
import com.tfg.bamashop.repositories.UserRepository;
import com.tfg.bamashop.services.RoleService;
import com.tfg.bamashop.services.UserService;
import com.tfg.security.dto.JwtDTO;
import com.tfg.security.dto.LoginDto;
import com.tfg.security.jwt.JwtProvider;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@Slf4j
@Tag(name = "AuthRestController", description = "AuthRestController API")
public class AuthRestController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	JwtProvider jwtProvider;

	@Operation(summary = "Login user", operationId = "login", description = "Login an user")
	@PostMapping("/login")
	public ResponseEntity<Object> login(@Valid @RequestBody LoginDto loginDto, BindingResult bindingResult) {

		log.info("Init login - AuthRestController");

		if (bindingResult.hasErrors())
			return new ResponseEntity<Object>("Incorrect credentials", HttpStatus.BAD_REQUEST);
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		List<User> user = userRepository.getUserByUsername(userDetails.getUsername());

		JwtDTO jwtDTO = new JwtDTO(user.get(0).getId(), jwt, userDetails.getUsername(), userDetails.getAuthorities());

		return ResponseEntity.ok(jwtDTO);
	}

	@Operation(summary = "Register user", operationId = "register", description = "Register an user")
	@PostMapping("/register")
	public ResponseEntity<Object> register(@Valid @RequestBody UserRegisterRequestDto userRegisterRequestDto) {
		UserDto user = new UserDto();
		try {
			user = userService.registerUser(userRegisterRequestDto);
		} catch (Exception e) {
			return ResponseEntity.ok("User failed to register");
		}

		return ResponseEntity.ok(user);
	}
}
