package com.example.wantedonboarding.jobopenings.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobOpeningsDto {

    //private Long openingId;

    private String openingTitle;

    private String openingContents;

    private String companyName;

    //private Long companyId;

    private String position;


}
