package com.example.sample2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sample2.Repository.UserDataRepository;
import com.example.sample2.model.User;

@Service
public class UserDataService {

	@Autowired
	private UserDataRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User registerNewUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword())); // パスワードをハッシュ化
		return userRepository.save(user);
	}

	public User validateUser(String email, String password) {
		User user = userRepository.findByEmail(email);
		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
			return user;
		}
		return null;
	}
}
