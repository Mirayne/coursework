package com.example.demo.services;

import com.example.demo.repo.MedicalsRepository;
import com.example.demo.view.MedicalsDTO;
import com.example.demo.view.converters.MedicalsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MedicalsService {
    private final MedicalsRepository medicalsRepository;
    private final MedicalsConverter medicalsConverter;

    @Autowired
    public MedicalsService(MedicalsRepository medicalsRepository, MedicalsConverter medicalsConverter) {
        this.medicalsRepository = medicalsRepository;
        this.medicalsConverter = medicalsConverter;
    }

    public List<MedicalsDTO> findAll() {
        return medicalsRepository.findAll()
                .stream()
                .map(medicalsConverter::toMedicalsDTO)
                .sorted(Comparator.comparing(MedicalsDTO::getMedicId))
                .collect(Collectors.toList());
    }

    public List<String> getEveryMedicName() {
        return findAll()
                .stream()
                .map(MedicalsDTO::getMedicName)
                .collect(Collectors.toList());
    }

    public MedicalsDTO findById(Integer id) {
        return medicalsConverter.toMedicalsDTO(medicalsRepository.findById(id).orElseThrow());
    }

    public void deleteMedical(Integer id) {
        medicalsRepository.delete(medicalsConverter.toMedicals(findById(id)));
    }

    public void saveMedical(MedicalsDTO medicalsDTO) {
        medicalsRepository.save(medicalsConverter.toMedicals(medicalsDTO));
    }

    public Map<Integer,String> medicalsAsMap() {
        return findAll().stream().collect(Collectors.toMap(MedicalsDTO::getMedicId, MedicalsDTO::getMedicName));
    }
}
