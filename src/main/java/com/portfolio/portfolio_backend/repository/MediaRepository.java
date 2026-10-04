package com.portfolio.portfolio_backend.repository;

import com.portfolio.portfolio_backend.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}