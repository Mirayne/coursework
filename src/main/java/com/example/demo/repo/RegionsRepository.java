package com.example.demo.repo;

import com.example.demo.entities.Regions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionsRepository extends JpaRepository<Regions, Integer> {
}
