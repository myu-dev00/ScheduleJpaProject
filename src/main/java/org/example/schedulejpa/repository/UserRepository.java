package org.example.schedulejpa.repository;

import org.example.schedulejpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}