package com.example.demo.repo;

import com.example.demo.entities.UsersTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersTable, Integer> {
    Optional<UsersTable> findByLogin(String login);
}
