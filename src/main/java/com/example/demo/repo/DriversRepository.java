package com.example.demo.repo;

import com.example.demo.entities.Drivers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriversRepository extends JpaRepository<Drivers, Integer> {
}
