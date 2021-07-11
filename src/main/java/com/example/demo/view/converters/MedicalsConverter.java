package com.example.demo.view.converters;

import com.example.demo.entities.Medicals;
import com.example.demo.entities.Specialties;
import com.example.demo.repo.MedicalsRepository;
import com.example.demo.repo.SpecialtiesRepository;
import com.example.demo.view.MedicalsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalsConverter {
    private final SpecialtiesRepository specialtiesRepository;

    @Autowired
    public MedicalsConverter(SpecialtiesRepository specialtiesRepository) {
        this.specialtiesRepository = specialtiesRepository;
    }

    public Medicals toMedicals(MedicalsDTO medicalsDTO) {
        Medicals medicals = new Medicals();

        medicals.setMedicId(medicalsDTO.getMedicId());
        medicals.setMedicName(medicalsDTO.getMedicName());
        medicals.setSpecialty(specialtiesRepository.findById(medicalsDTO.getSpecialtyDtoID()).orElseThrow());

        return medicals;
    }

    public MedicalsDTO toMedicalsDTO(Medicals medicals) {
        MedicalsDTO medicalsDTO = new MedicalsDTO();

        medicalsDTO.setMedicId(medicals.getMedicId());
        medicalsDTO.setMedicName(medicals.getMedicName());
        medicalsDTO.setSpecialtyDtoID(medicals.getSpecialty().getSpecialtyId());

        return medicalsDTO;
    }
}