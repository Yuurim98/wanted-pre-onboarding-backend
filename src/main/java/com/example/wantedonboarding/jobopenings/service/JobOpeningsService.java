package com.example.wantedonboarding.jobopenings.service;

import com.example.wantedonboarding.company.entity.CompanyEntity;
import com.example.wantedonboarding.company.repository.CompanyRepository;
import com.example.wantedonboarding.jobopenings.dto.JobOpeningsDto;
import com.example.wantedonboarding.jobopenings.entity.JobOpeningsEntity;
import com.example.wantedonboarding.jobopenings.repository.JobOpeningsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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


    public List<JobOpeningsDto> getAllJobOpeningList() {
        List<JobOpeningsEntity> jobOpenings = jobOpeningsRepository.findAll(Sort.by(Sort.Direction.DESC, "openingId"));
        return jobOpenings.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private JobOpeningsDto convertToDto(JobOpeningsEntity jobOpeningsEntity) {
        JobOpeningsDto dto = new JobOpeningsDto();
        dto.setOpeningId(jobOpeningsEntity.getOpeningId());
        dto.setCompanyId(jobOpeningsEntity.getCompany().getCompanyId());
        dto.setOpeningTitle(jobOpeningsEntity.getOpeningTitle());
        dto.setOpeningContents(jobOpeningsEntity.getOpeningTitle());
        dto.setSkill(jobOpeningsEntity.getSkill());
        dto.setPosition(jobOpeningsEntity.getPosition());
        return dto;
    }
}
