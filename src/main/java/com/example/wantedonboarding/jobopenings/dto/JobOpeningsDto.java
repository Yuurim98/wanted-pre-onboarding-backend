package com.example.wantedonboarding.jobopenings.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobOpeningsDto {

    private Long openingId;

    private Long companyId;

    @NotEmpty(message = "공고 제목을 입력하세요")
    private String openingTitle;

    @NotEmpty(message = "공고 내용을 입력하세요")
    private String openingContents;

    @NotEmpty(message = "사용기술을 입력하세요")
    private String skill;

    @NotEmpty(message = "포지션을 입력하세요")
    private String position;


}
