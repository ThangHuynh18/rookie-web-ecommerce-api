package com.rookie.webwatch.service;

import com.rookie.webwatch.entity.Status;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface StatusService {
    public List<Status> retrieveStatuses();

    public Optional<Status> getStatus(Long statusId);

    public Status saveStatus(Status status);

    public void deleteStatus(Long statusId) throws ResourceNotFoundException;

    public Status updatestatus(Status status);
}
