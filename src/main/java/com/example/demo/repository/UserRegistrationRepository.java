package com.example.demo.repository;

import com.example.demo.model.payload.authenticate.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Long> {
    Optional<UserRegistration> findByEmailAndVerificationCode(String email, String verificationCode);
}