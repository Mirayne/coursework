package com.example.demo.repo;

import com.example.demo.entities.CallingResults;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallingResultsRepository extends JpaRepository<CallingResults, Integer> {
}
