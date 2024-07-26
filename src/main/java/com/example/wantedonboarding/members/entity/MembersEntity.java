package com.example.wantedonboarding.members.entity;

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
@Table(name = "members")
public class MembersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 값 DB에서 자동 생성
    @Column(name = "member_id")
    private Long memberId;

    @Column(nullable = false, name = "member_name")
    private String memberName;

}
