package com.example.demo;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
          Student nam =  new Student(
                    "nam",
                    "nam@gmail.com",
                    LocalDate.of(2000, Month.SEPTEMBER,5)
            );

            Student bin =  new Student(
                    "bin",
                    "bin@gmail.com",
                    LocalDate.of(2002, Month.SEPTEMBER,9)
            );
            Student tuan =  new Student(
                    "tuan",
                    "tuan@gmail.com",
                    LocalDate.of(2001, Month.SEPTEMBER,5)
            );

            repository.saveAll(
                    List.of(nam,bin,tuan)
            );
        };
    }
}
