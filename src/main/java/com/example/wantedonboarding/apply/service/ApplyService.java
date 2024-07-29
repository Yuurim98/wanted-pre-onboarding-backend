package com.example.wantedonboarding.apply.service;

import com.example.wantedonboarding.apply.dto.ApplyDto;
import com.example.wantedonboarding.apply.entity.ApplyEntity;
import com.example.wantedonboarding.apply.repository.ApplyRepository;
import com.example.wantedonboarding.jobopenings.entity.JobOpeningsEntity;
import com.example.wantedonboarding.jobopenings.repository.JobOpeningsRepository;
import com.example.wantedonboarding.members.entity.MembersEntity;
import com.example.wantedonboarding.members.repository.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private JobOpeningsRepository jobOpeningsRepository;

    @Autowired
    private MembersRepository membersRepository;

    public void applyForJob(ApplyDto dto) {
        //채용 공고 조회
        JobOpeningsEntity jobOpening = jobOpeningsRepository.findById(dto.getOpeningId())
                .orElseThrow(() -> new RuntimeException("해당 채용 공고가 존재하지 않습니다."));

        //회원 정보 조회
        MembersEntity member = membersRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));

        //지원 여부 검증
        if (applyRepository.existsByJobOpeningAndMember(jobOpening, member)) {
            throw new RuntimeException("이미 해당 채용 공고에 지원하였습니다.");
        }


        //엔티티 set
        ApplyEntity applyEntity = new ApplyEntity();
        applyEntity.setJobOpening(jobOpening);
        applyEntity.setMember(member);

        //db 저장
        applyRepository.save(applyEntity);

    }
}
