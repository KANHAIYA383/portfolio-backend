package com.portfolio.portfolio_backend.repository;

import com.portfolio.portfolio_backend.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}