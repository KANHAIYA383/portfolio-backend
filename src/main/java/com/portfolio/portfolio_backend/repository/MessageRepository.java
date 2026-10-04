package com.portfolio.portfolio_backend.repository;

import com.portfolio.portfolio_backend.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
