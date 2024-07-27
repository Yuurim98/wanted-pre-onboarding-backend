package com.example.wantedonboarding.jobopenings.repository;

import com.example.wantedonboarding.jobopenings.entity.JobOpeningsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobOpeningsRepository extends JpaRepository<JobOpeningsEntity, Long> {


}
