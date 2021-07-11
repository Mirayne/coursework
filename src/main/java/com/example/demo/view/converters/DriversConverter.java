package com.example.demo.view.converters;

import com.example.demo.entities.Drivers;
import com.example.demo.repo.AutosRepository;
import com.example.demo.view.DriversDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriversConverter {
    public final AutosRepository autosRepository;

    @Autowired
    public DriversConverter(AutosRepository autosRepository) {
        this.autosRepository = autosRepository;
    }

    public Drivers toDrivers(DriversDTO driversDTO) {
        Drivers drivers = new Drivers();

        drivers.setDriverId(driversDTO.getDriverId());
        drivers.setCategoryOfDriver(driversDTO.getCategoryOfDriver());
        drivers.setNameOfDriver(driversDTO.getNameOfDriver());
        drivers.setExperienceOfDriver(driversDTO.getExperienceOfDriver());
        drivers.setAuto(autosRepository.findById(driversDTO.getAutoDtoID()).orElseThrow());

        return drivers;
    }

    public DriversDTO toDriversDTO(Drivers drivers) {
        DriversDTO driversDTO = new DriversDTO();

        driversDTO.setDriverId(drivers.getDriverId());
        driversDTO.setCategoryOfDriver(drivers.getCategoryOfDriver());
        driversDTO.setNameOfDriver(drivers.getNameOfDriver());
        driversDTO.setExperienceOfDriver(drivers.getExperienceOfDriver());
        driversDTO.setAutoDtoID(drivers.getAuto().getId());

        return driversDTO;
    }
}
