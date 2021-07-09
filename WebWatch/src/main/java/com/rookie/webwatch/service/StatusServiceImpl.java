package com.rookie.webwatch.service;

import com.rookie.webwatch.dto.StatusDTO;
import com.rookie.webwatch.entity.Status;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class StatusServiceImpl implements StatusService{

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<StatusDTO> retrieveStatuses() {
        List<Status> statuses = statusRepository.findAll();
        return new StatusDTO().toListDto(statuses);
    }

    @Override
    public StatusDTO getStatus(Long statusId) throws ResourceNotFoundException {
        Status status = statusRepository.findById(statusId).orElseThrow(() -> new ResourceNotFoundException("status not found for this id: "+statusId));
        return new StatusDTO().convertToDto(status);
    }

    @Override
    public StatusDTO saveStatus(StatusDTO statusDTO) {
        Status status = new StatusDTO().convertToEti(statusDTO);

        return new StatusDTO().convertToDto(statusRepository.save(status));
    }

    @Override
    public void deleteStatus(Long statusId) throws ResourceNotFoundException {
        Status status = statusRepository.findById(statusId).orElseThrow(() -> new ResourceNotFoundException("status not found for this id: " + statusId));
        this.statusRepository.delete(status);
    }

    @Override
    public StatusDTO updateStatus(Long id, StatusDTO statusDTO) throws ResourceNotFoundException {
        Status staExist = statusRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("status not found for this id: "+id));

        staExist.setStatus(statusDTO.getStatus());
        staExist.setStatusName(statusDTO.getStatusName());

        Status status = new Status();
        status = statusRepository.save(staExist);
        return new StatusDTO().convertToDto(status);
    }

}
