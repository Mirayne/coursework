package com.example.demo.services;

import com.example.demo.repo.CallingRepository;
import com.example.demo.view.CallingsDTO;
import com.example.demo.view.converters.CallingsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CallingsService {
    private final CallingRepository callingRepository;
    private final CallingsConverter callingsConverter;

    @Autowired
    public CallingsService(CallingRepository callingRepository, CallingsConverter callingsConverter) {
        this.callingRepository = callingRepository;
        this.callingsConverter = callingsConverter;
    }

    public List<CallingsDTO> findAll() {
        return callingRepository.findAll()
                .stream()
                .map(callingsConverter::toCallingsDTO)
                .sorted(Comparator.comparing(CallingsDTO::getId))
                .collect(Collectors.toList());
    }

    public void deleteCalling(Integer id) {
        callingRepository.delete(callingsConverter.toCallings(findById(id)));
    }

    public CallingsDTO findById(Integer id) {
        return callingsConverter.toCallingsDTO(callingRepository.findById(id)
                .orElseThrow());
    }

    public void saveCalling(CallingsDTO callingsDTO) {
        callingRepository.save(callingsConverter.toCallings(callingsDTO));
    }
}
