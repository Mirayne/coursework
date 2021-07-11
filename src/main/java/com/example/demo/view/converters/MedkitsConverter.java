package com.example.demo.view.converters;

import com.example.demo.entities.Medkits;
import com.example.demo.repo.ContentOfKitRepository;
import com.example.demo.view.MedkitsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedkitsConverter {
    private final ContentOfKitRepository contentOfKitRepository;

    @Autowired
    public MedkitsConverter(ContentOfKitRepository contentOfKitRepository) {
        this.contentOfKitRepository = contentOfKitRepository;
    }

    public Medkits toMedkits(MedkitsDTO medkitsDTO) {
        Medkits medkits = new Medkits();

        medkits.setMedkitId(medkitsDTO.getMedkitId());
        medkits.setContentOfKit(contentOfKitRepository.findById(medkitsDTO.getContentOfKitDtoID()).orElseThrow());
        medkits.setNumberOfMedkit(medkitsDTO.getNumberOfMedkit());
        medkits.setContentCount(medkitsDTO.getContentCount());

        return medkits;
    }

    public MedkitsDTO toMedkitsDTO(Medkits medkits) {
        MedkitsDTO medkitsDTO = new MedkitsDTO();

        medkitsDTO.setMedkitId(medkits.getMedkitId());
        medkitsDTO.setContentOfKitDtoID(medkits.getContentOfKit().getContentId());
        medkitsDTO.setNumberOfMedkit(medkits.getNumberOfMedkit());
        medkitsDTO.setContentCount(medkits.getContentCount());

        return medkitsDTO;
    }
}
