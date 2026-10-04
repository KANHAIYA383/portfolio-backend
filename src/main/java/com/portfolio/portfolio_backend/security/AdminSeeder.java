package com.portfolio.portfolio_backend.security;

import com.portfolio.portfolio_backend.entity.User;
import com.portfolio.portfolio_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${ADMIN_EMAIL:}")
    private String adminEmail;

    @Value("${ADMIN_NAME:Administrator}")
    private String adminName;

    @Value("${ADMIN_PASSWORD:}")
    private String adminPassword;

    public AdminSeeder(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (adminEmail == null ||
                adminEmail.isBlank() ||
                adminPassword == null ||
                adminPassword.isBlank()) {

            return;
        }

        if (userRepository.findByEmail(adminEmail).isPresent()) {
            return;
        }

        User admin = new User();

        admin.setName(adminName);
        admin.setEmail(adminEmail);
        admin.setPassword(
                passwordEncoder.encode(adminPassword)
        );
        admin.setRole("ADMIN");
        admin.setCreatedAt(LocalDateTime.now());

        userRepository.save(admin);

        System.out.println("Admin user created successfully.");
    }
}
