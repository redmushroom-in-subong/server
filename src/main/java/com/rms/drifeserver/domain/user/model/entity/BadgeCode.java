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
@Table(name = "bedge_code")
public class BedgeCode {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "bedge_name")
    private String bedge_name;

    

}
