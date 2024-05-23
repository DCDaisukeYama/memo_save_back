package com.example.sample2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample2.service.ImageService;

@RestController
@RequestMapping("/api/images") // ホスト名を除いたAPIのベースパス
public class ImageController {

	@Autowired
	private ImageService imageService;

	// userId パラメータを追加
	@PostMapping("/save")
	public ResponseEntity<?> saveImage(@RequestBody String imageData, @RequestParam Long userId) {
		imageService.saveImage(imageData, userId);
		return ResponseEntity.ok().build();
	}
}
