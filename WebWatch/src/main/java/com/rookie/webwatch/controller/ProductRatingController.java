package com.rookie.webwatch.controller;

import com.rookie.webwatch.dto.RatingDTO;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.service.ProductRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ratings")
public class ProductRatingController {
    @Autowired
    private ProductRatingService ratingService;

    @GetMapping("")
    public List<RatingDTO> getAllRating(){
        List<RatingDTO> ratings = ratingService.retrieveRatings();
        return ratings;
    }

    @GetMapping("/{rating_id}")
    public ResponseEntity<RatingDTO> findRating(@PathVariable("rating_id") Long ratingId) throws ResourceNotFoundException {
        RatingDTO ratingDTO = ratingService.getRating(ratingId);

        return ResponseEntity.ok(ratingDTO);
    }

    //save employee
    @PostMapping("/rating")
    public ResponseEntity<RatingDTO> createRating(@RequestBody RatingDTO ratingDTO) throws ResourceNotFoundException {
        RatingDTO dto = ratingService.saveRating(ratingDTO);
        return ResponseEntity.ok(dto);
    }
    //
//    //update
    @PutMapping("/rating/{rating_id}")
    public ResponseEntity<RatingDTO> updateRating(@PathVariable(value = "rating_id") Long ratingId,
                                                 @RequestBody RatingDTO ratingDTO) throws ResourceNotFoundException{
        RatingDTO updateRating = ratingService.updateRating(ratingId, ratingDTO);

        return new ResponseEntity<>(updateRating, HttpStatus.OK);
    }
    //
//    //delete
    @DeleteMapping("/rating/{rating_id}")
    public Map<String, Boolean> deleteRating(@PathVariable(value = "rating_id") Long ratingId)
            throws ResourceNotFoundException {
        ratingService.deleteRating(ratingId);
        Map<String, Boolean> reponse = new HashMap<>();
        reponse.put("deleted", Boolean.TRUE);

        return reponse;
    }
}
