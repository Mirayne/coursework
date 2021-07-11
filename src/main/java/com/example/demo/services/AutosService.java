package com.example.demo.services;

import com.example.demo.repo.AutosRepository;
import com.example.demo.view.AutosDTO;
import com.example.demo.view.converters.AutosConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutosService {
    private final AutosRepository autosRepository;
    private final AutosConverter autosConverter;

    @Autowired
    public AutosService(AutosRepository autosRepository, AutosConverter autosConverter) {
        this.autosRepository = autosRepository;
        this.autosConverter = autosConverter;
    }

    public List<AutosDTO> findAll() {
        return autosRepository.findAll()
                .stream()
                .map(autosConverter::toAutosDTO)
                .collect(Collectors.toList());
    }

    public void deleteAuto(Integer id) {
        autosRepository.delete(autosConverter.toAutos(findById(id)));
    }

    public void saveAuto(AutosDTO autosDTO) {
        autosRepository.save(autosConverter.toAutos(autosDTO));
    }

    public AutosDTO findById (Integer id) {
        return autosConverter.toAutosDTO(autosRepository.findById(id).orElseThrow());
    }


}
