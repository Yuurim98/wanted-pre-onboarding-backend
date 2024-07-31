package com.example.wantedonboarding.apply;

import com.example.wantedonboarding.apply.dto.ApplyDto;
import com.example.wantedonboarding.apply.repository.ApplyRepository;
import com.example.wantedonboarding.apply.service.ApplyService;
import com.example.wantedonboarding.company.entity.CompanyEntity;
import com.example.wantedonboarding.company.repository.CompanyRepository;
import com.example.wantedonboarding.jobopenings.entity.JobOpeningsEntity;
import com.example.wantedonboarding.jobopenings.repository.JobOpeningsRepository;
import com.example.wantedonboarding.jobopenings.service.JobOpeningsService;
import com.example.wantedonboarding.members.entity.MembersEntity;
import com.example.wantedonboarding.members.repository.MembersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ApplyServiceTest {

    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private ApplyService applyService;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobOpeningsRepository jobOpeningsRepository;

    @Autowired
    private MembersRepository membersRepository;

    private Long openingId;

    private Long memberId;


    @BeforeEach
    public void setUp() {
        //회사 엔티티 생성 및 저장
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setCompanyName("테스트 회사명");
        companyRepository.save(companyEntity);

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

        //회원 엔티티 생성 및 저장
        MembersEntity membersEntity = new MembersEntity();
        membersEntity.setMemberName("테스트");
        membersRepository.save(membersEntity);
        memberId = membersEntity.getMemberId();

    }

    @Test
    @DisplayName("공고 지원 성공")
    public void testApplyForJobSuccess() {
        //Given
        ApplyDto dto = new ApplyDto();
        dto.setOpeningId(openingId);
        dto.setMemberId(memberId);

        //When
        applyService.applyForJob(dto);

        //Then
        assertTrue(applyRepository.existsByJobOpeningAndMember( //applyRepository에 공고 엔티티와 회원 엔티티 존재 여부 확인
                jobOpeningsRepository.findById(openingId).get(), //공고 조회
                membersRepository.findById(memberId).get() //회원 조회
        ));
    }

    @Test
    @DisplayName("공고 지원 실패 : 유효하지 않은 공고 id")
    public void testApplyForJobInvalidOpeningId() {
        //Given
        ApplyDto applyDto = new ApplyDto();
        applyDto.setOpeningId(999L); //존재하지 않는 id
        applyDto.setMemberId(memberId);

        //When & Then
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            applyService.applyForJob(applyDto);
        });
        assertEquals("해당 채용 공고가 존재하지 않습니다.", thrown.getMessage());
    }

    @Test
    @DisplayName("공고 지원 실패 : 유효하지 않은 회원 id")
    public void testApplyForJobInvalidMemberId() {
        //Given
        ApplyDto applyDto = new ApplyDto();
        applyDto.setOpeningId(openingId);
        applyDto.setMemberId(999L); //존재하지 않는 id

        //When & Then
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            applyService.applyForJob(applyDto);
        });
        assertEquals("해당 회원이 존재하지 않습니다.", thrown.getMessage());
    }

    @Test
    @DisplayName("공고 지원 실패 : 중복 지원한 경우")
    public void testApplyForJobAlreadyApplied() {
        //Given
        ApplyDto applyDto = new ApplyDto();
        applyDto.setOpeningId(openingId);
        applyDto.setMemberId(memberId);

        //지원
        applyService.applyForJob(applyDto);

        //When & Then
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            applyService.applyForJob(applyDto); // Reapply
        });
        assertEquals("이미 해당 채용 공고에 지원하였습니다.", thrown.getMessage());
    }
}
