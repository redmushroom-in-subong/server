package com.rms.drifeserver.domain.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private Integer reviewSeq;
    private Integer writerSeq;
    private Integer vehicleSeq;
    private Integer driveScore;
    private String title;
    private String context;
    private String writeAt;
}
