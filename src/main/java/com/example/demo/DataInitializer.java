package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner init() {
        return args -> {
            if (userRepository.findByUsername("admin") == null) {
                ApplicationUser admin = new ApplicationUser();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin_password"));
                admin.setRole("ADMIN");
                userRepository.save(admin);
                logger.info("Admin user created: " + admin.getUsername());
            }
        };
    }
}
