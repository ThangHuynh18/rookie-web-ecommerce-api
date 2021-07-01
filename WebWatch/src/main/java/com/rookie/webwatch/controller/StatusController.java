package com.rookie.webwatch.controller;

import com.rookie.webwatch.entity.Status;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/statuses")
public class StatusController {
    @Autowired
    private StatusService statusService;

    @GetMapping("")
    public List<Status> getAllStatus(){
        List<Status> statuses = statusService.retrieveStatuses();
        return statuses;
    }

    @GetMapping("/{status_id}")
    public Optional<Status> findStatus(@PathVariable("status_id") Long statusId) throws ResourceNotFoundException {
        Optional<Status> status = Optional.ofNullable(statusService.getStatus(statusId)
                .orElseThrow(() -> new ResourceNotFoundException("status not found for this id: " + statusId)));

        return statusService.getStatus(statusId);
    }

    //save employee
    @PostMapping("/status")
    public Status createCategory(@RequestBody Status status){
        return statusService.saveStatus(status);
    }
    //
//    //update
    @PutMapping("/status/{status_id}")
    public ResponseEntity<Status> updateStatus(@PathVariable(value = "status_id") Long statusId,
                                                   @RequestBody Status statusDetail) throws ResourceNotFoundException{
        Status status = statusService.getStatus(statusId).orElseThrow(() -> new ResourceNotFoundException("status not found for this id: " +statusId));

        status.setStatus(statusDetail.getStatus());
        status.setStatusName(statusDetail.getStatusName());
        //status.setOrders(statusDetail.getOrders());

        return ResponseEntity.ok(statusService.updatestatus(status));
    }
    //
//    //delete
    @DeleteMapping("/status/{status_id}")
    public Map<String, Boolean> deleteStatus(@PathVariable(value = "status_id") Long statusId)
            throws ResourceNotFoundException {
        statusService.deleteStatus(statusId);
        Map<String, Boolean> reponse = new HashMap<>();
        reponse.put("deleted", Boolean.TRUE);

        return reponse;
    }
}
