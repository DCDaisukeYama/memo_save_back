package com.example.sample2.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("http://localhost:3000/api/images")
public class ImageController {

	@PostMapping("/save")
	public ResponseEntity<String> saveImage(@RequestBody ImageData data) {
		try {
			String imageDataBytes = data.getImage().substring(data.getImage().indexOf(",") + 1);
			byte[] bytes = Base64.getDecoder().decode(imageDataBytes);
			Path path = Paths.get("saved_image.png"); // 保存するファイル名
			Files.write(path, bytes);
			return ResponseEntity.ok("Image saved successfully");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body("Error saving image");
		}
	}

	static class ImageData {
		private String image;

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}
	}
}