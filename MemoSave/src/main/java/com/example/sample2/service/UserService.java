package com.example.sample2.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sample2.Repository.UserRepository;
import com.example.sample2.model.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User saveUser(User user) {
		//		パスワードをエンコード
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public boolean authenticate(String email, String password) {
		User user = userRepository.findByEmail(email);
		//		セッションIDの生成
		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
			String sessionId = UUID.randomUUID().toString(); // ランダムなセッションIDを生成
			user.setSessionId(sessionId); // セッションIDを設定
			userRepository.save(user); // 更新したユーザー情報を保存
			return true;
		}
		return false;
	}

	public User authenticateAndGetUser(String email, String password) {
		User user = userRepository.findByEmail(email);
		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
			String sessionId = UUID.randomUUID().toString();
			user.setSessionId(sessionId);
			userRepository.save(user);
			return user;
		}
		return null;
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
