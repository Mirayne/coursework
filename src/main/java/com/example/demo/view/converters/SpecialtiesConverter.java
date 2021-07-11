package com.example.demo.view.converters;

import com.example.demo.entities.Specialties;
import com.example.demo.view.SpecialtiesDTO;
import org.springframework.stereotype.Service;

@Service
public class SpecialtiesConverter {
    public Specialties toSpecialties(SpecialtiesDTO specialtiesDTO) {
        Specialties specialties = new Specialties();

        specialties.setSpecialtyId(specialtiesDTO.getSpecialtyId());
        specialties.setSpecialtyTitile(specialtiesDTO.getSpecialtyTitle());

        return specialties;
    }

    public SpecialtiesDTO toSpecialtiesDTO(Specialties specialties) {
        SpecialtiesDTO specialtiesDTO = new SpecialtiesDTO();

        specialtiesDTO.setSpecialtyId(specialties.getSpecialtyId());
        specialtiesDTO.setSpecialtyTitle(specialties.getSpecialtyTitile());

        return specialtiesDTO;
    }
}
