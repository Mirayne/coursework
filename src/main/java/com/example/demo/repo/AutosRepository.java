package com.example.demo.repo;

import com.example.demo.entities.Autos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutosRepository extends JpaRepository<Autos, Integer> {
}
