package com.example.wantedonboarding.jobopenings.service;

import com.example.wantedonboarding.company.entity.CompanyEntity;
import com.example.wantedonboarding.company.repository.CompanyRepository;
import com.example.wantedonboarding.jobopenings.dto.JobOpeningsDto;
import com.example.wantedonboarding.jobopenings.entity.JobOpeningsEntity;
import com.example.wantedonboarding.jobopenings.repository.JobOpeningsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JobOpeningsService {

    private final JobOpeningsRepository jobOpeningsRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public JobOpeningsService(JobOpeningsRepository jobOpeningsRepository, CompanyRepository companyRepository) {
        this.jobOpeningsRepository = jobOpeningsRepository;
        this.companyRepository = companyRepository;
    }

    //넘겨받은 dto를 엔티티로 변환한 뒤 DB 저장 및 저장된 엔티티 dto 변환 후 다시 반환
    public JobOpeningsDto createJobOpening(JobOpeningsDto dto) {
        JobOpeningsEntity jobOpeningsEntity = new JobOpeningsEntity();

        //companyId 검증
        CompanyEntity companyEntity = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new RuntimeException("유효하지 않은 companyId"));

        //엔티티 set
        jobOpeningsEntity.setCompany(companyEntity);
        jobOpeningsEntity.setOpeningTitle(dto.getOpeningTitle());
        jobOpeningsEntity.setOpeningContents(dto.getOpeningContents());
        jobOpeningsEntity.setSkill(dto.getSkill());
        jobOpeningsEntity.setPosition(dto.getPosition());

        //db 저장
        JobOpeningsEntity saveEntity = jobOpeningsRepository.save(jobOpeningsEntity);

        return new JobOpeningsDto(saveEntity.getOpeningId(),
                saveEntity.getCompany().getCompanyId(),
                saveEntity.getOpeningTitle(),
                saveEntity.getOpeningContents(),
                saveEntity.getSkill(),
                saveEntity.getPosition());
    }




    @Transactional
    public void deleteJobOpening(Long openingId) {
        //공고 존재 여부 검증
        if (!jobOpeningsRepository.existsById(openingId)) { //existsById의 반환값이 false면 DB에 존재X
            throw new RuntimeException("공고가 존재하지 않습니다");
        }

        //공고 삭제
        jobOpeningsRepository.deleteById(openingId);

        //공고 삭제 여부 검증
        if (jobOpeningsRepository.existsById(openingId)) { //existsById의 반환값이 true면 DB에 존재O
            throw new RuntimeException("공고 삭제에 실패했습니다");
        }
    }

}
