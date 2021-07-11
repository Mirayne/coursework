package com.example.demo.view.converters;

import com.example.demo.entities.Callings;
import com.example.demo.entities.MedBrigade;
import com.example.demo.entities.Status;
import com.example.demo.repo.MedBrigadeRepository;
import com.example.demo.repo.StatusRepository;
import com.example.demo.view.CallingsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CallingsConverter {
    public final MedBrigadeRepository medBrigadeRepository;
    public final StatusRepository statusRepository;

    @Autowired
    public CallingsConverter(MedBrigadeRepository medBrigadeRepository, StatusRepository statusRepository) {
        this.medBrigadeRepository = medBrigadeRepository;
        this.statusRepository = statusRepository;
    }

    public Callings toCallings(CallingsDTO callingsDTO) {
        Callings callings = new Callings();

        callings.setId(callingsDTO.getId());
        callings.setAddress(callingsDTO.getAddress());
        callings.setNameOfCaller(callingsDTO.getNameOfCaller());
        callings.setMedBrigade(medBrigadeRepository.findById(callingsDTO.getMedBrigadeDtoID()).orElseThrow());
        callings.setStatus(statusRepository.findById(callingsDTO.getStatusDtoID()).orElseThrow());
        callings.setCallingDateTime(LocalDateTime.parse(callingsDTO.getCallingDateTime()));

        return callings;
    }

    public CallingsDTO toCallingsDTO(Callings callings) {
        CallingsDTO callingsDTO = new CallingsDTO();

        callingsDTO.setAddress(callings.getAddress());
        callingsDTO.setId(callings.getId());
        callingsDTO.setNameOfCaller(callings.getNameOfCaller());
        callingsDTO.setMedBrigadeDtoID(callings.getMedBrigade().getNumberOfBrigade());
        callingsDTO.setStatusDtoID(callings.getStatus().getStatusId());
        callingsDTO.setCallingDateTime(callings.getCallingDateTime().toString());

        return callingsDTO;
    }
}
