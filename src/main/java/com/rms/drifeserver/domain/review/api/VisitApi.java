package com.rms.drifeserver.domain.review.api;

import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.review.service.VisitService;
import com.rms.drifeserver.domain.review.service.dto.request.AddReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class VisitApi {

    private final VisitService visitService;

    @PostMapping("/v1/stores/{storeId}/visit")
    public ApiResponse<Object> addVisit(@PathVariable Long storeId) {
        //인증 필요
        visitService.addVisit(1L, storeId);
        return ApiResponse.success(null);
    }
}
