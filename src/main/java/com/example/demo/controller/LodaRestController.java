package com.example.demo.controller;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.Store;
import com.example.demo.model.User;
import com.example.demo.model.payload.*;
import com.example.demo.model.payload.Response.ApiResponseError;
import com.example.demo.model.payload.authenticate.*;
import com.example.demo.repository.StoreRepository;
import com.example.demo.service.RegistrationService;
import com.example.demo.service.StoreService;
import com.example.demo.service.UserServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LodaRestController {

    @Autowired
    UserServices userServices;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RegistrationService registrationService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    StoreService storeService;
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            try {
                if (!loginRequest.getParentuser().equals(userServices.getParentUser(userDetails.getUser().getId()))) {
                    throw new UserNotFoundException("User not found with username: " + loginRequest.getParentuser());
                }

            }catch (UserNotFoundException ux){
                System.out.println(userServices.getParentUser(userDetails.getUser().getId()));
                ApiResponseError response = new ApiResponseError(HttpStatus.UNAUTHORIZED.value(), "gian hang không đúng.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            try{
                if(userServices.checkingUserStatus(userDetails.getUser().getId())){
                    throw new UserNotFoundException("Tài khoản chưa được xác minh ");
                }
            }catch (UserNotFoundException uxx){
                ApiResponseError response = new ApiResponseError(HttpStatus.UNAUTHORIZED.value(), uxx.getMessage());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            String jwt = jwtTokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
            UserData userData = new UserData((CustomUserDetails) authentication.getPrincipal(),
                    userServices.getParentUser(userDetails.getUser().getId()),
                    jwt);
            return ResponseEntity.ok(new LoginResponse("Đăng nhập thành công",userData));
        }catch (BadCredentialsException ex){
            ApiResponseError response = new ApiResponseError(HttpStatus.UNAUTHORIZED.value(), "Tài khoản hoặc mật khẩu không đúng.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest){
        boolean userNameExists = userServices.checkUserName(registerRequest.getUsername());

        if (userNameExists) {
            ApiResponseError response = new ApiResponseError(HttpStatus.UNAUTHORIZED.value(), "Tài khoản đã tồn tại");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        userServices.registerUser(registerRequest.getUsername(),registerRequest.getPassword(),storeService.findStoreByUserName(registerRequest.getDisplay_name()),registerRequest.getEmail());
        registrationService.registerUser(registerRequest);
        return ResponseEntity.ok(new RegisterReponse("vui lòng kiểm tra email vừa đăng ký để xác thực tài khoản","0"));
    }
    @PostMapping("/verify")
    public ResponseEntity<?> verifyAccount(@Valid @RequestBody VerifyAccount verifyAccount){
        try{
            if(registrationService.checkingVerifycode(verifyAccount.getVerificationCode(), verifyAccount.getEmail())){
                throw new UserNotFoundException("mã xác thực không hợp lệ");
            }
        }catch (UserNotFoundException userNotFoundException){
            ApiResponseError response = new ApiResponseError(HttpStatus.UNAUTHORIZED.value(), userNotFoundException.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        userServices.UpdateUserStatus(verifyAccount.getEmail());
        return ResponseEntity.ok(new RegisterReponse("Xác thực thành công","0"));
    }

    @GetMapping("/random")
    public ResponseEntity<?> randomStuff(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7); // Bỏ phần "Bearer " từ token
            boolean isValidToken = jwtTokenProvider.validateToken(token);

            if (isValidToken) {
                Long userId = jwtTokenProvider.getUserIdFromJWT(token);
                if (userId != null) {
                    // Xử lý userId ở đây, ví dụ: trả về userId trong ResponseEntity
                    return ResponseEntity.ok("User ID: " + userId);
                }
            } else {
                // Token không hợp lệ, trả về mã lỗi và thông điệp
                return ResponseEntity.status(200).body("error_code: 404, message: JWT không hợp lệ");
            }
        }
        return ResponseEntity.ok("loi");

    }
}
