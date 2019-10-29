package com.springBoot.example.sprinBootManager.repository;

import com.springBoot.example.sprinBootManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {
    Optional<User> findByName(String userName);
}
