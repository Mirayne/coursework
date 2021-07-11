package com.example.demo.services;

import com.example.demo.repo.ContentVendorsRepository;
import com.example.demo.view.ContentVendorsDTO;
import com.example.demo.view.converters.ContentVendorsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentVendorsService {
    private final ContentVendorsRepository contentVendorsRepository;
    private final ContentVendorsConverter contentVendorsConverter;

    @Autowired
    public ContentVendorsService(ContentVendorsRepository contentVendorsRepository, ContentVendorsConverter contentVendorsConverter) {
        this.contentVendorsRepository = contentVendorsRepository;
        this.contentVendorsConverter = contentVendorsConverter;
    }

    public List<ContentVendorsDTO> findAll() {
        return contentVendorsRepository.findAll()
                .stream()
                .map(contentVendorsConverter::toContentVendorsDTO)
                .sorted(Comparator.comparing(ContentVendorsDTO::getContentVendorId))
                .collect(Collectors.toList());
    }

    public void deleteContentVendor(Integer id) {
        contentVendorsRepository.delete(contentVendorsConverter.toContentVendors(findById(id)));
    }

    public ContentVendorsDTO findById(Integer id) {
        return contentVendorsConverter.toContentVendorsDTO(
                contentVendorsRepository.findById(id).orElseThrow());
    }

    public void saveContentVendor(ContentVendorsDTO contentVendorsDTO) {
        contentVendorsRepository.save(contentVendorsConverter.toContentVendors(contentVendorsDTO));
    }
}
