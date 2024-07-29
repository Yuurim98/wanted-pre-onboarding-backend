package com.example.wantedonboarding.members.repository;

import com.example.wantedonboarding.members.entity.MembersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembersRepository extends JpaRepository<MembersEntity, Long> {
}
