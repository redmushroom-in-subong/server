package com.rms.drifeserver.domain.review.api;

import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewApi {

    private final ReviewService reviewService;

    @PostMapping
    public Map<String,Object> VehicleAdd(@RequestBody Review review) {
        return null;
    }

    @GetMapping("/{review_seq}")
    public  Map<String,Object> vehicleDetails(@PathVariable("review_seq") String reviewSeq) {
        return null;
    }

    @DeleteMapping("/{review_seq}")
    public  void vehicleRemove(@PathVariable("review_seq") String reviewSeq) {

    }
}
