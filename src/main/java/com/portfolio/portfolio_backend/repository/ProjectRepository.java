
package com.portfolio.portfolio_backend.repository;

import com.portfolio.portfolio_backend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
