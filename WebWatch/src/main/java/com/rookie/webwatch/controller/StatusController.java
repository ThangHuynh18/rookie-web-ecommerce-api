package com.rookie.webwatch.controller;

import com.rookie.webwatch.dto.StatusDTO;

import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.service.StatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/statuses")
public class StatusController {
    @Autowired
    private StatusService statusService;

    @GetMapping("")
    public List<StatusDTO> getAllStatus(){
        List<StatusDTO> statuses = statusService.retrieveStatuses();
        return statuses;
    }

    @GetMapping("/{status_id}")
    public ResponseEntity<StatusDTO> findStatus(@PathVariable("status_id") Long statusId) throws ResourceNotFoundException {
        StatusDTO statusDTO = statusService.getStatus(statusId);

        return ResponseEntity.ok(statusDTO);
    }

    @PostMapping("/status")
    public ResponseEntity<StatusDTO> createStatus(@RequestBody StatusDTO statusDTO) {
        StatusDTO dto = statusService.saveStatus(statusDTO);
        return ResponseEntity.ok(dto);
    }

//    //update
    @PutMapping("/status/{status_id}")
    public ResponseEntity<StatusDTO> updateStatus(@PathVariable(value = "status_id") Long statusId,
                                                   @RequestBody StatusDTO statusDTO) throws ResourceNotFoundException{
        StatusDTO updateSta = statusService.updateStatus(statusId, statusDTO);

        return new ResponseEntity<>(updateSta, HttpStatus.OK);
    }

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
