package com.example.demo.repo;

import com.example.demo.entities.Specialties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtiesRepository extends JpaRepository<Specialties, Integer> {
}
