package com.example.demo.services;

import com.example.demo.entities.MedBrigade;
import com.example.demo.repo.MedBrigadeRepository;
import com.example.demo.view.MedBrigadeDTO;
import com.example.demo.view.converters.MedBrigadeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedBrigadeService {
    private final MedBrigadeRepository medBrigadeRepository;
    private final MedBrigadeConverter medBrigadeConverter;

    @Autowired
    public MedBrigadeService(MedBrigadeRepository medBrigadeRepository, MedBrigadeConverter medBrigadeConverter) {
        this.medBrigadeRepository = medBrigadeRepository;
        this.medBrigadeConverter = medBrigadeConverter;
    }

    public List<MedBrigadeDTO> findAll(){
        return medBrigadeRepository.findAll()
                .stream()
                .map(medBrigadeConverter::toMedBrigadeDTO)
                .sorted(Comparator.comparing(MedBrigadeDTO::getId))
                .collect(Collectors.toList());
    }

    public MedBrigadeDTO findById(Integer id) {
        return medBrigadeConverter.toMedBrigadeDTO(medBrigadeRepository.findById(id)
                .orElseThrow());
    }

    public void deleteMedbrigade(Integer id) {
        System.out.println(findById(id));
        MedBrigade medBrigade = medBrigadeConverter.toMedBrigade(findById(id));
        System.out.println(medBrigade.getNumberOfBrigade());
        medBrigadeRepository.delete(medBrigade);
    }

    public void saveMedBrigade(MedBrigadeDTO medBrigadeDTO) {
        medBrigadeRepository.save(medBrigadeConverter.toMedBrigade(medBrigadeDTO));
    }
}
