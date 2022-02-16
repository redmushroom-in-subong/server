package com.rms.drifeserver.domain.user.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "badge_code")
public class BadgeCode {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "badge_name")
    private String badge_name;

    

}
