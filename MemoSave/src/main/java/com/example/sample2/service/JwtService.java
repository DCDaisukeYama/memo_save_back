package com.example.sample2.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.sample2.model.User;

@Service
public class JwtService {

	private final String secretKey = "very_secret_key";

	public String generateToken(User user) {
		return Jwts.builder()
				.setSubject(user.getId().toString())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
