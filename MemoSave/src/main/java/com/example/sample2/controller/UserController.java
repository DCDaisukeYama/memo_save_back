package com.example.sample2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample2.model.User;
import com.example.sample2.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		User savedUser = userService.saveUser(user);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
		User user = userService.authenticateAndGetUser(email, password);
		if (user != null) {
			return ResponseEntity.ok(user); // レスポンスとしてユーザーオブジェクト全体を返す
		} else {
			//			認証に失敗した場合
			return ResponseEntity.status(401).build();
		}
	}

}
