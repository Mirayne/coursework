package com.example.demo.view.converters;

import com.example.demo.entities.SpentOfContent;
import com.example.demo.repo.CallingResultsRepository;
import com.example.demo.repo.ContentOfKitRepository;
import com.example.demo.view.SpentOfContentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpentOfContentConverter {
    private final ContentOfKitRepository contentOfKitRepository;
    private final CallingResultsRepository callingResultsRepository;

    @Autowired
    public SpentOfContentConverter(ContentOfKitRepository contentOfKitRepository, CallingResultsRepository callingResultsRepository) {
        this.contentOfKitRepository = contentOfKitRepository;
        this.callingResultsRepository = callingResultsRepository;
    }

    public SpentOfContent toSpentOfContent(SpentOfContentDTO spentOfContentDTO) {
        SpentOfContent spentOfContent = new SpentOfContent();

        spentOfContent.setUnitID(spentOfContentDTO.getUnitID());
        spentOfContent.setCallingResult(callingResultsRepository.findById(spentOfContentDTO.getResultID()).orElseThrow());
        spentOfContent.setThingsSpent(contentOfKitRepository.findById(spentOfContentDTO.getSpentThing()).orElseThrow());
        spentOfContent.setSpentCount(spentOfContentDTO.getSpentCount());

        return spentOfContent;
    }

    public SpentOfContentDTO toSpentOfContentDTO(SpentOfContent spentOfContent) {
        SpentOfContentDTO spentOfContentDTO = new SpentOfContentDTO();

        spentOfContentDTO.setUnitID(spentOfContent.getUnitID());
        spentOfContentDTO.setResultID(spentOfContent.getCallingResult().getResultID());
        spentOfContentDTO.setSpentThing(spentOfContent.getThingsSpent().getContentId());
        spentOfContentDTO.setSpentCount(spentOfContent.getSpentCount());

        return spentOfContentDTO;
    }
}
