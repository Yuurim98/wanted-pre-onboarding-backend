package com.example.wantedonboarding.jobopenings.repository;

import com.example.wantedonboarding.jobopenings.entity.JobOpeningsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOpeningsRepository extends JpaRepository<JobOpeningsEntity, Long> {

    List<JobOpeningsEntity> findByCompanyCompanyId(Long companyId);

    // 회사 이름으로 검색
    @Query("SELECT j FROM JobOpeningsEntity j WHERE j.companyName LIKE %:search%")
    Page<JobOpeningsEntity> findByCompanyNameContainingIgnoreCase(@Param("search") String search, Pageable pageable);

    // 사용 기술로 검색
    @Query("SELECT j FROM JobOpeningsEntity j WHERE j.skill LIKE %:search%")
    Page<JobOpeningsEntity> findBySkillContainingIgnoreCase(@Param("search") String search, Pageable pageable);

}
