package com.bookstore.app.repository;

import com.bookstore.app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findFirstByEmail(String email);
}
