package com.example.demo.controller;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.Store;
import com.example.demo.model.payload.*;
import com.example.demo.model.payload.Response.ApiResponseError;
import com.example.demo.model.payload.authenticate.LoginRequest;
import com.example.demo.model.payload.authenticate.LoginResponse;
import com.example.demo.model.payload.authenticate.RegisterRequest;
import com.example.demo.repository.StoreRepository;
import com.example.demo.service.StoreService;
import com.example.demo.service.UserServices;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LodaRestController {

    @Autowired
    UserServices userServices;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


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
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest){
//        try{
//            if(userServices.checkUserName(registerRequest.getUsername())){
//                throw new UserNotFoundException("User da ton tai with username: " + registerRequest.getUsername());
//            }
//        }catch (UserNotFoundException ux){
//            ApiResponseError response = new ApiResponseError(HttpStatus.UNAUTHORIZED.value(), "Tài khoản đã tồn tại");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//        }
//
//        if(userServices.checkStoreName(registerRequest.getDisplay_name())){
//            Store store = new Store();
//            store.setUserName(registerRequest.getDisplay_name());
//            storeService.saveStore(store);
//        }
//
//    }

    @GetMapping("/random")
    public RandomStuff randomStuff(){
        return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này");
    }
}
