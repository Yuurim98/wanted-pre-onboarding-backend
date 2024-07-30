package com.example.wantedonboarding.jobopenings;


import com.example.wantedonboarding.company.entity.CompanyEntity;
import com.example.wantedonboarding.company.repository.CompanyRepository;
import com.example.wantedonboarding.jobopenings.dto.JobOpeningsDto;
import com.example.wantedonboarding.jobopenings.repository.JobOpeningsRepository;
import com.example.wantedonboarding.jobopenings.service.JobOpeningsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class JobOpeningsServiceTest {

    @Autowired
    private JobOpeningsService jobOpeningsService;

    @Autowired
    private JobOpeningsRepository jobOpeningsRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private Long companyId;

    @BeforeEach
    public void setUp() {
        //테스트 실행 전 설정
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setCompanyName("테스트 회사명");
        companyRepository.save(companyEntity);
        companyId = companyEntity.getCompanyId();
    }


    @Test
    @DisplayName("test 공고 등록 성공")
    public void testCreateJobOpeningSuccess() {
        //Given
        JobOpeningsDto jobOpeningsDto = new JobOpeningsDto();
        jobOpeningsDto.setCompanyId(companyId); //setUp() 에서 설정한 companyId 사용
        jobOpeningsDto.setOpeningTitle("공고 제목");
        jobOpeningsDto.setOpeningContents("공고 내용");
        jobOpeningsDto.setSkill("공고 기술");
        jobOpeningsDto.setPosition("공고 포지션");

        //When
        JobOpeningsDto resultDto = jobOpeningsService.createJobOpening(jobOpeningsDto);

        //Then
        assertNotNull(resultDto);
        assertEquals(jobOpeningsDto.getOpeningTitle(), resultDto.getOpeningTitle());
        assertEquals(jobOpeningsDto.getOpeningContents(), resultDto.getOpeningContents());
        assertEquals(jobOpeningsDto.getSkill(), resultDto.getSkill());
        assertEquals(jobOpeningsDto.getPosition(), resultDto.getPosition());
    }

    @Test
    @DisplayName("test 유효하지 않은 회사Id")
    public void testCreateJobOpeningCompanyNotFound() {
        //Given
        JobOpeningsDto jobOpeningsDto = new JobOpeningsDto();
        jobOpeningsDto.setCompanyId(1199L);

        //When & Then
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            jobOpeningsService.createJobOpening(jobOpeningsDto);
        });

        assertEquals("유효하지 않은 companyId", runtimeException.getMessage());
    }


}
