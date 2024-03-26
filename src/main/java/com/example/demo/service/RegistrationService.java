package com.example.demo.service;

import com.example.demo.model.payload.authenticate.UserRegistration;
import com.example.demo.repository.UserRegistrationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.example.demo.model.payload.authenticate.RegisterRequest;

import java.util.Random;

@Service
public class RegistrationService {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Autowired
    private JavaMailSender javaMailSender;
    @Transactional
    public void registerUser(RegisterRequest registerRequest) {
        // Thực hiện kiểm tra và lưu trữ thông tin tài khoản mới vào cơ sở dữ liệu

        // Tạo mã xác thực ngẫu nhiên
        String verificationCode = generateVerificationCode();

        // Lưu thông tin đăng ký người dùng và mã xác thực vào cơ sở dữ liệu
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setEmail(registerRequest.getEmail());
        userRegistration.setVerificationCode(verificationCode);
        userRegistrationRepository.save(userRegistration);

        // Gửi email xác thực
        sendVerificationEmail(registerRequest.getEmail(), verificationCode);
    }
    public void sendVerificationEmail(String email, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Xác thực tài khoản");
        message.setText("Mã xác thực của bạn là: " + verificationCode);
        javaMailSender.send(message);
    }
    private String generateVerificationCode() {
        // Tạo mã xác thực ngẫu nhiên, ví dụ: mã OTP
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}
