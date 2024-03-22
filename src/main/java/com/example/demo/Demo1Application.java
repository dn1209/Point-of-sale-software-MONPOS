package com.example.demo;

import com.example.demo.model.Store;
import com.example.demo.model.User;
import com.example.demo.repository.StoreRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;


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

    @Override
    public void run(String... args) throws Exception {
        // Khi chương trình chạy
        // Insert vào csdl một user.
        User user = new User();
        Store store = new Store();
        store.setUser(user);
        store.setUserName("namm");
        user.setUserName("nam");
        user.setPassword(passwordEncoder.encode("nam"));
        storeRepository.save(store);
        userRepository.save(user);
        System.out.println(user);
        System.out.println(store);

    }


}
