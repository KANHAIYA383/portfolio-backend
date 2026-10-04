package com.portfolio.portfolio_backend.repository;

import com.portfolio.portfolio_backend.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
