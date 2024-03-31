package com.example.demo.repository;

import com.example.demo.model.payload.authenticate.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Long> {
    Optional<UserRegistration> findByEmailAndVerificationCode(String email, String verificationCode);


    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN false ELSE true END FROM UserRegistration u WHERE u.email = :email AND u.verificationCode= :verifycode")
    boolean existsByEmailAndCode(String email,String verifycode);

}