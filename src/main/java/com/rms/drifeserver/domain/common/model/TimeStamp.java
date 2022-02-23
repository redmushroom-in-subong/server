package com.rms.drifeserver.domain.common.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class TimeStamp {
    private LocalDateTime createdAt;
}
