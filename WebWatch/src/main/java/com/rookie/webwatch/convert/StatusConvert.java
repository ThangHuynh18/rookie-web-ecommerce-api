package com.rookie.webwatch.convert;

import com.rookie.webwatch.dto.StatusDTO;
import com.rookie.webwatch.entity.Status;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StatusConvert {
    @Autowired
    private ModelMapper modelMapper;

    public StatusDTO convertToDto(Optional<Status> status) {
        StatusDTO statusDTO = modelMapper.map(status, StatusDTO.class);
        statusDTO.setStatus_id(status.get().getStatus_id());
        statusDTO.setStatus(status.get().getStatus());
        statusDTO.setStatusName(status.get().getStatusName());

        return statusDTO;
    }


    public StatusDTO convertToDtoForUpdate(Status status) {
        StatusDTO statusDTO = modelMapper.map(status, StatusDTO.class);
        statusDTO.setStatus_id(status.getStatus_id());
        statusDTO.setStatus(status.getStatus());
        statusDTO.setStatusName(status.getStatusName());

        return statusDTO;
    }


    public Status convertToEntity(StatusDTO statusDTO) {
        Status status = modelMapper.map(statusDTO, Status.class);

        return status;
    }
}
