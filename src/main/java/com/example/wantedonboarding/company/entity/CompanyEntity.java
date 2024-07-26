package com.example.wantedonboarding.company.entity;

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
@Table(name = "company")
public class CompanyEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 값 DB에서 자동 생성
    @Column(name = "company_id")
    private Long companyId;

    @Column(nullable = false, name = "company_name") //null값 허용 금지 및 컬럼명 지정
    private String companyName;

}
