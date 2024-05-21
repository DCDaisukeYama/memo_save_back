package com.example.sample2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sample2.model.User;

@Repository
public interface UserDataRepository extends JpaRepository<User, Long> {
	User findByEmail(String email); // 特定のメールアドレスを持つユーザーを探すメソッド
}
