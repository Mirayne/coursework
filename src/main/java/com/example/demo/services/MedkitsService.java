package com.example.demo.services;

import com.example.demo.repo.MedkitsRepository;
import com.example.demo.view.MedkitsDTO;
import com.example.demo.view.converters.MedkitsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MedkitsService {
    private final MedkitsRepository medkitsRepository;
    private final MedkitsConverter medkitsConverter;

    @Autowired
    public MedkitsService(MedkitsRepository medkitsRepository, MedkitsConverter medkitsConverter) {
        this.medkitsRepository = medkitsRepository;
        this.medkitsConverter = medkitsConverter;
    }

    public List<MedkitsDTO> findAll() {
        return medkitsRepository.findAll()
                .stream()
                .map(medkitsConverter::toMedkitsDTO)
                .sorted(Comparator.comparing(MedkitsDTO::getMedkitId))
                .collect(Collectors.toList());
    }

    public MedkitsDTO findById(Integer id) {
        return medkitsConverter.toMedkitsDTO(medkitsRepository.findById(id).orElseThrow());
    }

    public void deleteMedkit(Integer id) {
        medkitsRepository.delete(medkitsConverter.toMedkits(findById(id)));
    }

    public void saveMedkit(MedkitsDTO medkitsDTO) {
        medkitsRepository.save(medkitsConverter.toMedkits(medkitsDTO));
    }

    public Map<Integer,Integer> medkitsAsMap() {
        return findAll().stream().collect(Collectors.toMap(MedkitsDTO::getMedkitId, MedkitsDTO::getNumberOfMedkit));
    }

    public Set<Integer> medkitsAsSet() {
        return findAll().stream().map(MedkitsDTO::getNumberOfMedkit).collect(Collectors.toSet());
    }
}
