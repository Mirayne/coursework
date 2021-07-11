package com.example.demo.view.converters;

import com.example.demo.entities.Status;
import com.example.demo.view.StatusDTO;
import org.springframework.stereotype.Service;

@Service
public class StatusConverter {
    public Status toStatus(StatusDTO statusDTO) {
        Status status = new Status();

        status.setStatusId(statusDTO.getStatusId());
        status.setStatusName(statusDTO.getStatusName());

        return status;
    }

    public StatusDTO toStatusDTO(Status status) {
        StatusDTO statusDTO = new StatusDTO();

        statusDTO.setStatusId(status.getStatusId());
        statusDTO.setStatusName(status.getStatusName());

        return statusDTO;
    }
}
