package com.rookie.webwatch.service;

import com.rookie.webwatch.dto.StatusDTO;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;


public interface StatusService {
    public List<StatusDTO> retrieveStatuses();

    public StatusDTO getStatus(Long statusId) throws ResourceNotFoundException;

    public StatusDTO saveStatus(StatusDTO statusDTO);

    public void deleteStatus(Long statusId) throws ResourceNotFoundException;

    public StatusDTO updateStatus(Long id, StatusDTO statusDTO) throws ResourceNotFoundException;
}
