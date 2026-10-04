package com.portfolio.portfolio_backend.repository;

import com.portfolio.portfolio_backend.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}