package com.example.sample2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sample2.model.Image;

@Repository
public interface ImageDataRepository extends JpaRepository<Image, Long> {
	// ここにカスタムクエリメソッドを追加することができます。
	// 例: 特定のユーザーに属する画像を見つけるメソッド
	List<Image> findByUserId(Long userId);
}