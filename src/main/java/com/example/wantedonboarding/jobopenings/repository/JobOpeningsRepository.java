package com.example.wantedonboarding.jobopenings.repository;

import com.example.wantedonboarding.jobopenings.entity.JobOpeningsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOpeningsRepository extends JpaRepository<JobOpeningsEntity, Long> {

    List<JobOpeningsEntity> findByCompanyCompanyId(Long companyId);
}
