package com.example.demo.repo;

import com.example.demo.entities.Medkits;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedkitsRepository extends JpaRepository<Medkits, Integer> {
}
