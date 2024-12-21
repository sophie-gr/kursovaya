package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    boolean existsByUsernameAndRole(String username, String role);
    ApplicationUser findByUsername(String username);
}
