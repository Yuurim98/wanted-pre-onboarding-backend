package com.example.wantedonboarding.jobopenings.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobOpeningUpdateDto {

    private String openingTitle;

    private String openingContents;

    private String skill;

    private String position;
}
