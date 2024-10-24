package com.example.ToDo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ToDo.dto.LoginDTO;
import com.example.ToDo.dto.TokenDTO;
import com.example.ToDo.dto.UserDTO;
import com.example.ToDo.dto.UserDTOEdit;
import com.example.ToDo.security.JwtTokenUtil;
import com.example.ToDo.security.JwtUserDetailsService;
import com.example.ToDo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("auth")
@Validated
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private final AuthenticationManager authenticationManager;
	private final UserService userService;
	private final JwtTokenUtil jwtTokenUtil;
	private final JwtUserDetailsService userDetailsService;

	@Autowired
	public UserController(UserService userService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService) {
		this.userService = userService;
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userDetailsService = userDetailsService;
	}

	@PostMapping("register")
	public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDTO) {
		System.out.println("Login " + userDTO.toString());

		logger.info("Registering user with email: {}", userDTO.getEmail());
		UserDTO createdUser = userService.add(userDTO);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@PostMapping("login")
	public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
		} catch (DisabledException e) {
			logger.error("User disabled: {}", loginDTO.getEmail(), e);
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			logger.error("Invalid credentials for user: {}", loginDTO.getEmail(), e);
			throw new Exception("INVALID_CREDENTIALS", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new TokenDTO(token));
	}

	@PutMapping("edit")
	public ResponseEntity<UserDTO> edit(@RequestBody UserDTOEdit userDTOEdit) {
		logger.info("Editing user with email: {}", userDTOEdit.getEmail());
		UserDTO updatedUser = userService.edit(userDTOEdit);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {
		logger.error("An error occurred: ", e);
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}