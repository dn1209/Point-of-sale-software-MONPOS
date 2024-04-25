package com.example.demo;

import com.example.demo.model.GroupProduct;
import com.example.demo.model.Store;
import com.example.demo.model.User;
import com.example.demo.model.payload.authenticate.RegisterRequest;
import com.example.demo.repository.GroupProductRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.GroupProductService;
import com.example.demo.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


@SpringBootApplication
public class Demo1Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }

    @Autowired
    UserRepository userRepository;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RegistrationService registrationService;
    @Autowired
    GroupProductService groupProductService;
    @Override
    public void run(String... args) throws Exception {
//        LocalDate today = LocalDate.now();
//        GroupProduct gr = new GroupProduct();
//        // Khi chương trình chạy
//        // Insert vào csdl một user.
////        User user = new User();
//        Store store = new Store();
//        store.setUserName("namm");
//        storeRepository.save(store);
//        gr.setStore(store);
//        gr.setPrdGroupName("do da dung");
//        gr.setCreatedAt(LocalDate.now());
////        user.setUserName("nam");
////        user.setEmail("ducnam967@yahoo.com.vn");
////        user.setUserStatus("1");
////        user.setPassword(passwordEncoder.encode("nam"));
////        user.setStore(store);
////        user.setDisplayName("demotest9");
////        user.setCreated(LocalDate.now());
////        user.setLogined(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()));
////        user.setUpdated(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()));
//        groupProductService.save(gr);
////        userRepository.save(user);
////        System.out.println(user);
//        System.out.println(store);
////        RegisterRequest registerRequest = new RegisterRequest();
////        registerRequest.setEmail("hoangdangducnam@gmail.com");
////        registrationService.registerUser(registerRequest);

    }


}
