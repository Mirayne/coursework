package com.example.demo.view.converters;

import com.example.demo.entities.MedBrigade;
import com.example.demo.repo.DriversRepository;
import com.example.demo.repo.MedicalsRepository;
import com.example.demo.repo.MedkitsRepository;
import com.example.demo.repo.RegionsRepository;
import com.example.demo.view.MedBrigadeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedBrigadeConverter {
    private final DriversRepository driversRepository;
    private final MedicalsRepository medicalsRepository;
    private final MedkitsRepository medkitsRepository;
    private final RegionsRepository regionsRepository;

    @Autowired
    public MedBrigadeConverter(DriversRepository driversRepository, MedicalsRepository medicalsRepository, MedkitsRepository medkitsRepository, RegionsRepository regionsRepository) {
        this.driversRepository = driversRepository;
        this.medicalsRepository = medicalsRepository;
        this.medkitsRepository = medkitsRepository;
        this.regionsRepository = regionsRepository;
    }

    public MedBrigade toMedBrigade(MedBrigadeDTO medBrigadeDTO) {
        MedBrigade medBrigade = new MedBrigade();

        medBrigade.setNumberOfBrigade(medBrigadeDTO.getId());
        medBrigade.setDriver(driversRepository.findById(medBrigadeDTO.getDriversDtoID()).orElseThrow());
        medBrigade.setMedic1(medicalsRepository.findById(medBrigadeDTO.getFirstMedicalsDtoID()).orElseThrow());
        medBrigade.setMedic2(medicalsRepository.findById(medBrigadeDTO.getSecondMedicalsDtoID()).orElseThrow());
        medBrigade.setMedic3(medicalsRepository.findById(medBrigadeDTO.getThirdMedicalsDtoID()).orElseThrow());
        medBrigade.setMedkit(medkitsRepository.findById(medBrigadeDTO.getMedkitDtoID()).orElseThrow());
        medBrigade.setRegion(regionsRepository.findById(medBrigadeDTO.getRegionDtoID()).orElseThrow());

        return medBrigade;
    }

    public MedBrigadeDTO toMedBrigadeDTO(MedBrigade medBrigade) {
        MedBrigadeDTO medBrigadeDTO = new MedBrigadeDTO();

        medBrigadeDTO.setId(medBrigade.getNumberOfBrigade());
        medBrigadeDTO.setDriversDtoID(medBrigade.getDriver().getDriverId());
        medBrigadeDTO.setFirstMedicalsDtoID(medBrigade.getMedic1().getMedicId());
        medBrigadeDTO.setSecondMedicalsDtoID(medBrigade.getMedic2().getMedicId());
        medBrigadeDTO.setThirdMedicalsDtoID(medBrigade.getMedic3().getMedicId());
        medBrigadeDTO.setMedkitDtoID(medBrigade.getMedkit().getMedkitId());
        medBrigadeDTO.setRegionDtoID(medBrigade.getRegion().getRegionId());

        return medBrigadeDTO;
    }
}
