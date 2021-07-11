package com.example.demo.services;

import com.example.demo.repo.ContentOfKitRepository;
import com.example.demo.view.ContentOfKitDTO;
import com.example.demo.view.converters.ContentOfKitConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ContentOfKitService {
    private final ContentOfKitRepository contentOfKitRepository;
    private final ContentOfKitConverter contentOfKitConverter;

    @Autowired
    public ContentOfKitService(ContentOfKitRepository contentOfKitRepository, ContentOfKitConverter contentOfKitConverter) {
        this.contentOfKitRepository = contentOfKitRepository;
        this.contentOfKitConverter = contentOfKitConverter;
    }

    public List<ContentOfKitDTO> findAll() {
        return contentOfKitRepository.findAll()
                .stream()
                .map(contentOfKitConverter::toContentOfKitDTO)
                .sorted(Comparator.comparing(ContentOfKitDTO::getContentId))
                .collect(Collectors.toList());
    }

    public void deleteContentOfKit(Integer id) {
        contentOfKitRepository.delete(contentOfKitConverter.toContentOfKit(findContentOfKitById(id)));
    }

    public void saveContentOfKit(ContentOfKitDTO contentOfKitDTO) {
        contentOfKitRepository.save(contentOfKitConverter.toContentOfKit(contentOfKitDTO));
    }

    public ContentOfKitDTO findContentOfKitById(Integer id) {
        return contentOfKitConverter.toContentOfKitDTO(
                contentOfKitRepository.findById(id)
                .orElseThrow());
    }

    public Map<Integer, String> findAsMap() {
        return findAll().stream().collect(Collectors.toMap(ContentOfKitDTO::getContentId, ContentOfKitDTO::getContentTitle));
    }
}
