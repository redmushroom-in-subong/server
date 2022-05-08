package com.rms.drifeserver.domain.user.api;

import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.user.service.MyReviewService;
import com.rms.drifeserver.domain.user.service.dto.response.MyReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.rms.drifeserver.domain.common.dto.ApiResponse.*;

@RestController
@RequestMapping("v1/users/reviews")
@RequiredArgsConstructor
public class MyReviewApi {
    final private MyReviewService myReviewService;
    @GetMapping("")
    @ResponseBody
    ApiResponse getMyReviews(){
        return success(myReviewService.getMyReviews());
    }
}
