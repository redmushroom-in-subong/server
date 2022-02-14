package com.rms.drifeserver.domain.review.api;

import com.rms.drifeserver.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewApi {

    private final ReviewService reviewService;
}
