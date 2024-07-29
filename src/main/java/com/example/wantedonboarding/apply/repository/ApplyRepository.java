package com.example.wantedonboarding.apply.repository;

import com.example.wantedonboarding.apply.entity.ApplyEntity;
import com.example.wantedonboarding.jobopenings.entity.JobOpeningsEntity;
import com.example.wantedonboarding.members.entity.MembersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyRepository extends JpaRepository<ApplyEntity, Long> {

    boolean existsByJobOpeningAndMember(JobOpeningsEntity jobOpening, MembersEntity member);
}
