package com.example.sample2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sample2.Repository.ImageRepository;
import com.example.sample2.model.Image;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;

	public Image saveImage(String imageData, Long userId) {
		Image image = new Image();
		image.setImageData(imageData);
		image.setUserId(userId);
		return imageRepository.save(image);
	}

	// 特定のユーザーに紐づく画像データを取得するメソッドも考えられます
	public List<Image> getImagesByUserId(Long userId) {
		return imageRepository.findByUserId(userId);
	}
}
