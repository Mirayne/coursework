package com.example.demo.services;

import com.example.demo.repo.MedBrigadeRepository;
import com.example.demo.view.TestDTO;
import com.example.demo.view.converters.TestConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestService {
    private final MedBrigadeRepository medBrigadeRepository;
    private final TestConverter testConverter;

    @Autowired
    public TestService(MedBrigadeRepository medBrigadeRepository, TestConverter testConverter) {
        this.medBrigadeRepository = medBrigadeRepository;
        this.testConverter = testConverter;
    }

    public List<TestDTO> findAll(){
        return medBrigadeRepository.findAll()
                .stream()
                .map(testConverter::testConvert)
                .collect(Collectors.toList());
    }
}
