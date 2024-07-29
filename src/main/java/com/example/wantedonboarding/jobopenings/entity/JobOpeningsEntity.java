package com.example.wantedonboarding.jobopenings.entity;

import com.example.wantedonboarding.company.entity.CompanyEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    //다대일 관계: 여러개의 채용 공고는 하나의 회사에 속함
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company; //회사id

    @Column(nullable = false, name = "company_name")
    private String companyName;

    @Column(nullable = false, name = "opening_title") //null값 허용 금지 및 컬럼명 지정
    private String openingTitle; //공고 제목

    @Column(name = "opening_contents")
    private String openingContents; //공고 내용

    private String skill; //사용 기술

    @Column(name = "position")
    private String position; //포지션

}
