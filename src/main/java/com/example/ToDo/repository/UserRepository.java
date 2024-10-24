package com.example.ToDo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ToDo.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
}