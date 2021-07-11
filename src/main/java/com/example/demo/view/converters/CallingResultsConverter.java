package com.example.demo.view.converters;

import com.example.demo.entities.CallingResults;
import com.example.demo.entities.Callings;
import com.example.demo.entities.SpentOfContent;
import com.example.demo.repo.CallingRepository;
import com.example.demo.repo.SpentOfContentRepository;
import com.example.demo.view.CallingResultsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class CallingResultsConverter {
    private final CallingRepository callingRepository;
    private final SpentOfContentRepository spentOfContentRepository;

    @Autowired
    public CallingResultsConverter(CallingRepository callingRepository, SpentOfContentRepository spentOfContentRepository) {
        this.callingRepository = callingRepository;
        this.spentOfContentRepository = spentOfContentRepository;
    }

    public CallingResults toCallingResults(CallingResultsDTO callingResultsDTO) {
        CallingResults callingResults = new CallingResults();

        callingResults.setCalling(callingRepository.findById(callingResultsDTO.getCallingID()).orElseThrow());
        callingResults.setResultID(callingResultsDTO.getResultID());
        callingResults.setResultDescription(callingResultsDTO.getResultDescription());
        callingResults.setCompletionDate(LocalDateTime.parse(callingResultsDTO.getCompletionDate()));

        return callingResults;
    }

    public CallingResultsDTO toCallingResultsDTO(CallingResults callingResults) {
        CallingResultsDTO callingResultsDTO = new CallingResultsDTO();

        callingResultsDTO.setCallingID(callingResults.getCalling().getId());
        callingResultsDTO.setResultID(callingResults.getResultID());
        callingResultsDTO.setResultDescription(callingResults.getResultDescription());
        callingResultsDTO.setCompletionDate(callingResults.getCompletionDate().toString());

        Map<String, Integer> spentMap = new HashMap<>();
        for (SpentOfContent s: spentOfContentRepository.findAll()){
            if (s.getCallingResult().getResultID() == callingResults.getResultID())
                spentMap.put(s.getThingsSpent().getContentTitle(), s.getSpentCount());
        }

        callingResultsDTO.setSpent(spentMap);

        return callingResultsDTO;
    }
}
