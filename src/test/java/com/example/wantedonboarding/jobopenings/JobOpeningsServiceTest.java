package com.example.wantedonboarding.jobopenings;


import com.example.wantedonboarding.company.entity.CompanyEntity;
import com.example.wantedonboarding.company.repository.CompanyRepository;
import com.example.wantedonboarding.jobopenings.dto.JobOpeningUpdateDto;
import com.example.wantedonboarding.jobopenings.dto.JobOpeningsDto;
import com.example.wantedonboarding.jobopenings.entity.JobOpeningsEntity;
import com.example.wantedonboarding.jobopenings.repository.JobOpeningsRepository;
import com.example.wantedonboarding.jobopenings.service.JobOpeningsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    private Long openingId;

    @BeforeEach
    public void setUp() {
        //회사 엔티티 생성 및 저장
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setCompanyName("테스트 회사명");
        companyRepository.save(companyEntity);
        companyId = companyEntity.getCompanyId();

        //공고 엔티티 생성 및 저장
        JobOpeningsEntity jobOpeningsEntity = new JobOpeningsEntity();
        jobOpeningsEntity.setCompany(companyEntity);
        jobOpeningsEntity.setCompanyName(companyEntity.getCompanyName());
        jobOpeningsEntity.setOpeningTitle("제목");
        jobOpeningsEntity.setOpeningContents("내용");
        jobOpeningsEntity.setSkill("기술");
        jobOpeningsEntity.setPosition("포지션");
        jobOpeningsRepository.save(jobOpeningsEntity);
        openingId = jobOpeningsEntity.getOpeningId();
    }


    @Test
    @DisplayName("공고 등록 성공")
    public void testCreateJobOpeningSuccess() {
        //Given
        JobOpeningsDto jobOpeningsDto = new JobOpeningsDto();
        jobOpeningsDto.setCompanyId(companyId); //setUp() 에서 설정한 id 사용
        jobOpeningsDto.setOpeningTitle("제목");
        jobOpeningsDto.setOpeningContents("내용");
        jobOpeningsDto.setSkill("기술");
        jobOpeningsDto.setPosition("포지션");

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
    @DisplayName("공고 등록 실패 : 유효하지 않은 회사Id")
    public void testJobOpeningCompanyNotFound() {
        //Given
        JobOpeningsDto jobOpeningsDto = new JobOpeningsDto();
        jobOpeningsDto.setCompanyId(1199L); //존재하지 않는 id

        //When & Then
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            jobOpeningsService.createJobOpening(jobOpeningsDto);
        });

        assertEquals("유효하지 않은 companyId", runtimeException.getMessage());
    }

    @Test
    @DisplayName("공고 수정 성공 : 일부 필드만")
    public void testUpdateJobOpeningPartialUpdate() {
        //Given
        JobOpeningUpdateDto updateDto = new JobOpeningUpdateDto();
        updateDto.setOpeningTitle("제목 수정");
        updateDto.setPosition("포지션 수정");

        //When
        JobOpeningsDto resultDto = jobOpeningsService.updateJobOpening(openingId, updateDto);

        //Then
        assertNotNull(resultDto);
        assertEquals(updateDto.getOpeningTitle(), resultDto.getOpeningTitle());
        assertEquals("내용", resultDto.getOpeningContents());
        assertEquals("기술", resultDto.getSkill());
        assertEquals(updateDto.getPosition(), resultDto.getPosition());
    }

    @Test
    @DisplayName("공고 수정 성공 : 모든 필드")
    public void testUpdateJobOpeningAllFields() {
        //Given
        JobOpeningUpdateDto updateDto = new JobOpeningUpdateDto();
        updateDto.setOpeningTitle("제목 수정");
        updateDto.setOpeningContents("내용 수정");
        updateDto.setSkill("기술 수정");
        updateDto.setPosition("포지션 수정");

        //When
        JobOpeningsDto resultDto = jobOpeningsService.updateJobOpening(openingId, updateDto);

        //Then
        assertNotNull(resultDto);
        assertEquals(updateDto.getOpeningTitle(), resultDto.getOpeningTitle());
        assertEquals(updateDto.getOpeningContents(), resultDto.getOpeningContents());
        assertEquals(updateDto.getSkill(), resultDto.getSkill());
        assertEquals(updateDto.getPosition(), resultDto.getPosition());
    }

    @Test
    @DisplayName("공고 수정 실패 : 유효하지 않은 공고 ID")
    public void testUpdateJobOpeningNotFound() {
        //Given
        JobOpeningUpdateDto updateDto = new JobOpeningUpdateDto();
        updateDto.setOpeningTitle("제목 수정"); //수정할 데이터 설정

        //When & Then
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            jobOpeningsService.updateJobOpening(999L, updateDto); //존재하지 않는 id
        });
        assertEquals("해당하는 공고가 없습니다", thrown.getMessage());
    }

    @Test
    @DisplayName("공고 삭제 성공")
    public void testDeleteJobOpeningSuccess() {
        //When
        jobOpeningsService.deleteJobOpening(openingId);

        //Then
        assertFalse(jobOpeningsRepository.findById(openingId).isPresent()); //false라면 공고 삭제된 상태
    }


    @Test
    @DisplayName("공고 삭제 실패: 유효하지 않은 공고 ID")
    public void testDeleteJobOpeningNotFound() {
        //When & Then
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            jobOpeningsService.deleteJobOpening(999L); //존재하지 않는 id
        });
        assertEquals("공고가 존재하지 않습니다", thrown.getMessage());
    }

}
