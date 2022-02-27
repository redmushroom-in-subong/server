package com.rms.drifeserver.domain.review.api;

import com.rms.drifeserver.domain.review.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class VisitApi {

    private final VisitService visitService;
}
