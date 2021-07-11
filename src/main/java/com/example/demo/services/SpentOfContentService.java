package com.example.demo.services;

import com.example.demo.entities.SpentOfContent;
import com.example.demo.repo.SpentOfContentRepository;
import com.example.demo.view.SpentOfContentDTO;
import com.example.demo.view.converters.SpentOfContentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpentOfContentService {
    private final SpentOfContentRepository spentOfContentRepository;
    private final SpentOfContentConverter spentOfContentConverter;

    @Autowired
    public SpentOfContentService(SpentOfContentRepository spentOfContentRepository, SpentOfContentConverter spentOfContentConverter) {
        this.spentOfContentRepository = spentOfContentRepository;
        this.spentOfContentConverter = spentOfContentConverter;
    }

    public List<SpentOfContentDTO> findAll(Integer resultID) {
        return spentOfContentRepository.findAll()
                .stream()
                .map(spentOfContentConverter::toSpentOfContentDTO)
                .filter(o -> o.getResultID().equals(resultID))
                .collect(Collectors.toList());
    }
    public SpentOfContentDTO findById(Integer id) {
        return spentOfContentConverter.toSpentOfContentDTO(spentOfContentRepository.findById(id)
        .orElseThrow());
    }

    public void deleteSpentOfContent(Integer id) {
        spentOfContentRepository.delete(spentOfContentConverter.toSpentOfContent(findById(id)));
    }

    public void saveSpentOfContent(SpentOfContentDTO spentOfContentDTO) {
        spentOfContentRepository.save(spentOfContentConverter.toSpentOfContent(spentOfContentDTO));
    }
}
