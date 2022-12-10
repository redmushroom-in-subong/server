package com.rms.drifeserver.test.review.dto.request;

import com.rms.drifeserver.domain.review.service.dto.request.AddReviewRequest;
import com.rms.drifeserver.domain.review.service.dto.request.UpdateReviewRequest;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdateReviewRequestTest {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void UpdateReviewRequest_NotBlank_체크() {
        //given
        Long notFoundStoreId = -1L;

        String contents = " ";
        List<Long> keywordIds = new ArrayList<>(Arrays.asList(1L,3L));
        List<String> imageUrls = new ArrayList<>(Arrays.asList("test1.png","test2.png"));

        UpdateReviewRequest request = UpdateReviewRequest.testBuilder()
                .contents(contents)
                .keywordIds(keywordIds)
                .imageUrls(imageUrls)
                .build();
        //when
        Set<ConstraintViolation<UpdateReviewRequest>> violations = validator.validate(request);

        //then
        assertThat(violations.size()).isEqualTo(1);
    }
}
