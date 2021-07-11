package com.example.demo.services;

import com.example.demo.repo.DriversRepository;
import com.example.demo.view.DriversDTO;
import com.example.demo.view.converters.DriversConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DriversService {
    private final DriversRepository driversRepository;
    private final DriversConverter driversConverter;

    @Autowired
    public DriversService(DriversRepository driversRepository, DriversConverter driversConverter) {
        this.driversRepository = driversRepository;
        this.driversConverter = driversConverter;
    }

    public List<DriversDTO> findAll() {
        return driversRepository.findAll()
                .stream()
                .map(driversConverter::toDriversDTO)
                .sorted(Comparator.comparing(DriversDTO::getDriverId))
                .collect(Collectors.toList());
    }

    public List<String> getAllNames() {
        return findAll().stream().map(DriversDTO::getNameOfDriver).collect(Collectors.toList());
    }

    public void deleteDriver(Integer id) {
        driversRepository.delete(driversConverter.toDrivers(findById(id)));
    }

    public DriversDTO findById(Integer id) {
        return driversConverter.toDriversDTO(driversRepository.findById(id).orElseThrow());
    }

    public void saveDriver(DriversDTO driversDTO) {
        driversRepository.save(driversConverter.toDrivers(driversDTO));
    }

    public Map<Integer,String> driversAsMap() {
        return findAll().stream().collect(Collectors.toMap(DriversDTO::getDriverId, DriversDTO::getNameOfDriver));
    }
}
