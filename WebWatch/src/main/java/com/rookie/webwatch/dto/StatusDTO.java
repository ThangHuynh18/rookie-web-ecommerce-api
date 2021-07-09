package com.rookie.webwatch.dto;

import com.rookie.webwatch.entity.Status;

import java.util.ArrayList;
import java.util.List;

public class StatusDTO {
    private long status_id;
    private long status;
    private String statusName;

    public StatusDTO() {
    }


    public StatusDTO convertToDto(Status status) {
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setStatus_id(status.getStatus_id());
        statusDTO.setStatus(status.getStatus());
        statusDTO.setStatusName(status.getStatusName());

        return statusDTO;
    }

    public Status convertToEti(StatusDTO statusDTO) {
        Status status = new Status();

        status.setStatus(statusDTO.getStatus());
        status.setStatusName(statusDTO.getStatusName());

        return status;
    }


    public List<StatusDTO> toListDto(List<Status> listEntity) {
        List<StatusDTO> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.convertToDto(e));
        });
        return listDto;
    }


    public long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(long status_id) {
        this.status_id = status_id;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
