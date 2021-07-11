package com.example.demo.repo;

import com.example.demo.entities.Callings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallingRepository extends JpaRepository<Callings, Integer> {
}
