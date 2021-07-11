package com.example.demo.services;

import com.example.demo.repo.SpecialtiesRepository;
import com.example.demo.view.SpecialtiesDTO;
import com.example.demo.view.converters.SpecialtiesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SpecialtiesService {
    private final SpecialtiesRepository specialtiesRepository;
    private final SpecialtiesConverter specialtiesConverter;

    @Autowired
    public SpecialtiesService(SpecialtiesRepository specialtiesRepository, SpecialtiesConverter specialtiesConverter) {
        this.specialtiesRepository = specialtiesRepository;
        this.specialtiesConverter = specialtiesConverter;
    }

    public List<SpecialtiesDTO> findAll() {
        return specialtiesRepository.findAll()
                .stream()
                .map(specialtiesConverter::toSpecialtiesDTO)
                .sorted(Comparator.comparing(SpecialtiesDTO::getSpecialtyId))
                .collect(Collectors.toList());
    }

    public SpecialtiesDTO findById(Integer id) {
        return specialtiesConverter.toSpecialtiesDTO(specialtiesRepository.findById(id).orElseThrow());
    }

    public void deleteSpecialty(Integer id) {
        specialtiesRepository.delete(specialtiesConverter.toSpecialties(findById(id)));
    }

    public void saveSpecialty(SpecialtiesDTO specialtiesDTO) {
        specialtiesRepository.save(specialtiesConverter.toSpecialties(specialtiesDTO));
    }

    public Map<Integer, String> findAsMap() {
        return findAll().stream().collect(Collectors.toMap(SpecialtiesDTO::getSpecialtyId, SpecialtiesDTO::getSpecialtyTitle));
    }
}
