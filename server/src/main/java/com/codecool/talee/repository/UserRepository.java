package com.codecool.talee.repository;

import com.codecool.talee.model.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean deleteUserById(Long id);
    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);
}
