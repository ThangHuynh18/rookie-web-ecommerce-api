package com.rookie.webwatch.service;

import com.rookie.webwatch.entity.Status;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StatusServiceImpl implements StatusService{

    @Autowired
    private StatusRepository statusRepository;

    public void setStatusRepository(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public List<Status> retrieveStatuses() {
        List<Status> statuses = statusRepository.findAll();
        return statuses;
    }

    @Override
    public Optional<Status> getStatus(Long statusId) {
        return statusRepository.findById(statusId);
    }

    @Override
    public Status saveStatus(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public void deleteStatus(Long statusId) throws ResourceNotFoundException {
        Status status = statusRepository.findById(statusId).orElseThrow(() -> new ResourceNotFoundException("status not found for this id: " + statusId));
        this.statusRepository.delete(status);
    }

    @Override
    public Status updatestatus(Status status) {
        return statusRepository.save(status);
    }
}
