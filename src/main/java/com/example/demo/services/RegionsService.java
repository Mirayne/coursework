package com.example.demo.services;

import com.example.demo.repo.RegionsRepository;
import com.example.demo.view.RegionsDTO;
import com.example.demo.view.converters.RegionsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionsService {
    private final RegionsRepository regionsRepository;
    private final RegionsConverter regionsConverter;

    @Autowired
    public RegionsService(RegionsRepository regionsRepository, RegionsConverter regionsConverter) {
        this.regionsRepository = regionsRepository;
        this.regionsConverter = regionsConverter;
    }

    public List<RegionsDTO> findAll() {
        return regionsRepository.findAll()
                .stream()
                .map(regionsConverter::toRegionsDTO)
                .sorted(Comparator.comparing(RegionsDTO::getRegionId))
                .collect(Collectors.toList());
    }

    public RegionsDTO findById(Integer id) {
        return regionsConverter.toRegionsDTO(regionsRepository.findById(id).orElseThrow());
    }

    public void deleteRegion(Integer id) {
        regionsRepository.delete(regionsConverter.toRegions(findById(id)));
    }

    public void saveRegion(RegionsDTO regionsDTO) {
        regionsRepository.save(regionsConverter.toRegions(regionsDTO));
    }
}
