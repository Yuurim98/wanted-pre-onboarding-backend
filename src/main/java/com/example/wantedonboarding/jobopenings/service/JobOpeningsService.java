package com.example.wantedonboarding.jobopenings.service;

import com.example.wantedonboarding.company.entity.CompanyEntity;
import com.example.wantedonboarding.company.repository.CompanyRepository;
import com.example.wantedonboarding.jobopenings.dto.JobOpeningUpdateDto;
import com.example.wantedonboarding.jobopenings.dto.JobOpeningsDto;
import com.example.wantedonboarding.jobopenings.entity.JobOpeningsEntity;
import com.example.wantedonboarding.jobopenings.repository.JobOpeningsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /* 채용공고 등록
    *  dto를 엔티티로 변환한 뒤 DB 저장 및 저장된 엔티티 dto 변환 후 다시 반환
    * */
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

<<<<<<< HEAD
    /* 채용공고 수정
    *  updateDto가 null이 아닌 경우에만 jobOpeningsEntity를 set
    * */
    public JobOpeningsDto updateJobOpening(Long openingId, JobOpeningUpdateDto updateDto) {
        JobOpeningsEntity jobOpeningsEntity = jobOpeningsRepository.findById(openingId)
                .orElseThrow(() -> new RuntimeException("해당하는 공고가 없습니다"));

        if (updateDto.getOpeningTitle() != null) {
            jobOpeningsEntity.setOpeningTitle(updateDto.getOpeningTitle());
        }

        if (updateDto.getOpeningContents() != null) {
            jobOpeningsEntity.setOpeningContents(updateDto.getOpeningContents());
        }

        if (updateDto.getSkill() != null) {
            jobOpeningsEntity.setSkill(updateDto.getSkill());
        }

        if (updateDto.getPosition() != null) {
            jobOpeningsEntity.setPosition(updateDto.getPosition());
        }

        JobOpeningsEntity updateEntity = jobOpeningsRepository.save(jobOpeningsEntity);
        return new JobOpeningsDto(updateEntity.getOpeningId(),
                updateEntity.getCompany().getCompanyId(),
                updateEntity.getOpeningTitle(),
                updateEntity.getOpeningContents(),
                updateEntity.getSkill(),
                updateEntity.getPosition());
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
