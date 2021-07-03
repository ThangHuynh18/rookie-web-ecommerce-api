package com.rookie.webwatch.controller;

import com.rookie.webwatch.entity.ProductRating;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.service.ProductRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/ratings")
public class ProductRatingController {
    @Autowired
    private ProductRatingService ratingService;

    @GetMapping("")
    public List<ProductRating> getAllRating(){
        List<ProductRating> ratings = ratingService.retrieveRatings();
        return ratings;
    }

    @GetMapping("/{rating_id}")
    public Optional<ProductRating> findRating(@PathVariable("rating_id") Long ratingId) throws ResourceNotFoundException {
        Optional<ProductRating> rating = Optional.ofNullable(ratingService.getRating(ratingId)
                .orElseThrow(() -> new ResourceNotFoundException("rating not found for this id: " + ratingId)));

        return ratingService.getRating(ratingId);
    }

    //save employee
    @PostMapping("/rating")
    public ProductRating createRating(@RequestBody ProductRating rating){
        return ratingService.saveRating(rating);
    }
    //
//    //update
    @PutMapping("/rating/{rating_id}")
    public ResponseEntity<ProductRating> updateRating(@PathVariable(value = "rating_id") Long ratingId,
                                                 @RequestBody ProductRating ratingDetail) throws ResourceNotFoundException{
        ProductRating rating = ratingService.getRating(ratingId).orElseThrow(() -> new ResourceNotFoundException("rating not found for this id: " +ratingId));

        rating.setRatingNumber(ratingDetail.getRatingNumber());
//        rating.setProduct(ratingDetail.getProduct());
//        rating.setUser(ratingDetail.getUser());

        return ResponseEntity.ok(ratingService.updateRating(rating));
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
