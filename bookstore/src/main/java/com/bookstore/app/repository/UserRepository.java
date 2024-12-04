package com.bookstore.app.repository;

import com.bookstore.app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findFirstByEmail(String email);
}
