package com.example.sample2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample2.model.User;
import com.example.sample2.config.SecurityConfig;
import com.example.sample2.service.UserDataService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserDataService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		if (userService.existsByEmail(user.getEmail())) {
			return ResponseEntity.badRequest().body("Email already in use");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.save(user);
		return ResponseEntity.ok("User registered successfully");
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) {
		User foundUser = userService.authenticateUser(user.getEmail(), user.getPassword());
		if (foundUser != null) {
			String token = userService.generateJwtToken(foundUser);
			return ResponseEntity.ok().body(token);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
		}
	}
}
