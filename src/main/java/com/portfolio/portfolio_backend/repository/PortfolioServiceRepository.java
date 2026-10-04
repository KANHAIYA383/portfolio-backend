
package com.portfolio.portfolio_backend.repository;

import com.portfolio.portfolio_backend.entity.PortfolioService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioServiceRepository extends JpaRepository<PortfolioService, Long> {
}
