package com.example.wantedonboarding.company.entity;

import com.example.wantedonboarding.jobopenings.entity.JobOpeningsEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company")
public class CompanyEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 값 DB에서 자동 생성
    @Column(name = "company_id")
    private Long companyId;

    @Column(nullable = false, name = "company_name") //null값 허용 금지 및 컬럼명 지정
    private String companyName;

    //일대다 관계: 하나의 회사는 여러개의 채용 공고를 가질 수 있음
    @OneToMany(mappedBy = "company")
    private Set<JobOpeningsEntity> jobOpenings;

}
