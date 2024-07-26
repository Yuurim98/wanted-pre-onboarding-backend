package com.example.wantedonboarding.jobopenings.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job_openings")
public class JobOpeningsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 값 DB에서 자동 생성
    @Column(name = "opening_id")
    private Long openingId;

    @Column(nullable = false, name = "opening_title") //null값 허용 금지 및 컬럼명 지정
    private String openingTitle;

    @Column(name = "opening_contents")
    private String openingContents;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "position")
    private String position;

}
