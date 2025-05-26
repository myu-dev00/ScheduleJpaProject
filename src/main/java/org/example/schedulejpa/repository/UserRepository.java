package org.example.schedulejpa.repository;

import org.example.schedulejpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); //email 옵셔널 설정
}