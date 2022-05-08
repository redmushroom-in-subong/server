
package com.rms.drifeserver.domain.user.service;

import com.rms.drifeserver.domain.user.dao.MyReviewRepository;
import com.rms.drifeserver.domain.user.dao.MyStoreRepository;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.dto.response.MyReviewResponse;
import com.rms.drifeserver.domain.user.service.dto.response.MyStoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyReviewServiceImpl implements MyReviewService{
    final private MyReviewRepository myReviewRepository;
    final private UserService userService;
    @Override
    public List<MyReviewResponse> getMyReviews(){
        User user=userService.getUserEntity();
        return myReviewRepository.findMyReviews(user.getId());
    }
}
