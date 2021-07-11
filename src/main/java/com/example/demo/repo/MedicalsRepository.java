package com.example.demo.repo;

import com.example.demo.entities.Medicals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalsRepository extends JpaRepository<Medicals, Integer> {
}
