package com.example.wantedonboarding.apply.entity;

import com.example.wantedonboarding.company.entity.CompanyEntity;
import com.example.wantedonboarding.jobopenings.entity.JobOpeningsEntity;
import com.example.wantedonboarding.members.entity.MembersEntity;
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
@Table(name = "apply_history", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"opening_id", "member_id"})
})
public class ApplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 값 DB에서 자동 생성
    @Column(name = "apply_id")
    private Long applyId;

    @ManyToOne
    @JoinColumn(name = "opening_id", nullable = false)
    private JobOpeningsEntity jobOpening;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private MembersEntity member;

}
