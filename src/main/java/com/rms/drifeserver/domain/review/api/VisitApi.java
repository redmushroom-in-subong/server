package com.rms.drifeserver.domain.review.api;

import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.review.service.VisitService;
import com.rms.drifeserver.domain.review.service.dto.request.AddReviewRequest;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class VisitApi {

    private final VisitService visitService;
    private final UserService userService;

    @PostMapping("/v1/stores/{storeId}/visit")
    public ApiResponse<Object> addVisit(@PathVariable Long storeId) {
        User user = userService.getUserEntity();

        visitService.addVisit(user, storeId);
        return ApiResponse.success(null);
    }
}
