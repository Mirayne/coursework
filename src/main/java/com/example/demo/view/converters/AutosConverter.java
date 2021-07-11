package com.example.demo.view.converters;

import com.example.demo.entities.Autos;
import com.example.demo.view.AutosDTO;
import org.springframework.stereotype.Service;

@Service
public class AutosConverter {
    public Autos toAutos(AutosDTO autosDTO) {
        Autos autos = new Autos();

        autos.setId(autosDTO.getId());
        autos.setMakerOfAuto(autosDTO.getMaker());
        autos.setModelOfAuto(autosDTO.getModel());

        return autos;
    }

    public AutosDTO toAutosDTO(Autos autos) {
        AutosDTO autosDTO = new AutosDTO();

        autosDTO.setId(autos.getId());
        autosDTO.setMaker(autos.getMakerOfAuto());
        autosDTO.setModel(autos.getModelOfAuto());

        return autosDTO;
    }
}
