package com.rookie.webwatch.service;

import com.rookie.webwatch.dto.StatusDTO;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;


public interface StatusService {
    public List<StatusDTO> retrieveStatuses();

    public Optional<StatusDTO> getStatus(Long statusId) throws ResourceNotFoundException;

    public StatusDTO saveStatus(StatusDTO statusDTO);

    public Boolean deleteStatus(Long statusId) throws ResourceNotFoundException;

    public StatusDTO updateStatus(Long id, StatusDTO statusDTO) throws ResourceNotFoundException;
}
