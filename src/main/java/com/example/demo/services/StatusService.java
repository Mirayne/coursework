package com.example.demo.services;

import com.example.demo.repo.StatusRepository;
import com.example.demo.view.StatusDTO;
import com.example.demo.view.converters.StatusConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusService {
    private final StatusRepository statusRepository;
    private final StatusConverter statusConverter;

    @Autowired
    public StatusService(StatusRepository statusRepository, StatusConverter statusConverter) {
        this.statusRepository = statusRepository;
        this.statusConverter = statusConverter;
    }

    public List<StatusDTO> findAll() {
        return statusRepository.findAll()
                .stream()
                .map(statusConverter::toStatusDTO)
                .sorted(Comparator.comparing(StatusDTO::getStatusId))
                .collect(Collectors.toList());
    }

    public StatusDTO findById(Integer id) {
        return statusConverter.toStatusDTO(statusRepository.findById(id).orElseThrow());
    }

    public void deleteStatus(Integer id) {
        statusRepository.delete(statusConverter.toStatus(findById(id)));
    }

    public void saveStatus(StatusDTO statusDTO) {
        statusRepository.save(statusConverter.toStatus(statusDTO));
    }

    public List<String> getListOfStatusNames() {
        return findAll().stream().map(StatusDTO::getStatusName).collect(Collectors.toList());
    }
}
