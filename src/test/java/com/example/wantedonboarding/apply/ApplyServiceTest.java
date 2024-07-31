package com.example.wantedonboarding.apply;

import com.example.wantedonboarding.apply.repository.ApplyRepository;
import com.example.wantedonboarding.company.entity.CompanyEntity;
import com.example.wantedonboarding.jobopenings.entity.JobOpeningsEntity;
import com.example.wantedonboarding.jobopenings.repository.JobOpeningsRepository;
import com.example.wantedonboarding.members.entity.MembersEntity;
import com.example.wantedonboarding.members.repository.MembersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class ApplyServiceTest {

    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private JobOpeningsRepository jobOpeningsRepository;

    @Autowired
    private MembersRepository membersRepository;

    private Long openingId;

    private Long mamberId;

    @BeforeEach
    public void setUp() {
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
        mamberId = membersEntity.getMemberId();
    }
}
