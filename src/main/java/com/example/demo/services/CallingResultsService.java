package com.example.demo.services;

import com.example.demo.repo.CallingResultsRepository;
import com.example.demo.view.CallingResultsDTO;
import com.example.demo.view.converters.CallingResultsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CallingResultsService {
    private final CallingResultsRepository callingResultsRepository;
    private final CallingResultsConverter callingResultsConverter;

    @Autowired
    public CallingResultsService(CallingResultsRepository callingResultsRepository, CallingResultsConverter callingResultsConverter) {
        this.callingResultsRepository = callingResultsRepository;
        this.callingResultsConverter = callingResultsConverter;
    }

    public List<CallingResultsDTO> findAll() {
        return callingResultsRepository.findAll()
                .stream()
                .map(callingResultsConverter::toCallingResultsDTO)
                .sorted(Comparator.comparing(CallingResultsDTO::getCallingID))
                .collect(Collectors.toList());
    }

    public void deleteResult(Integer id) {
        callingResultsRepository.delete(callingResultsConverter.toCallingResults(findById(id)));
    }

    public CallingResultsDTO findById(Integer id) {
        return callingResultsConverter.toCallingResultsDTO(callingResultsRepository.findById(id)
                                                            .orElseThrow());
    }

    public void saveResult(CallingResultsDTO callingResultsDTO) {
        callingResultsRepository.save(callingResultsConverter.toCallingResults(callingResultsDTO));
    }
}
