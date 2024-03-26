package com.example.demo.controller;

import com.example.demo.model.payload.Response.ApiResponse;
import com.example.demo.model.Student;
import com.example.demo.service.ResponseService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService =  studentService;
    }

    @GetMapping
    public ResponseEntity<Object> getStudents() {
        List<Student> students = studentService.getStudents();

        ApiResponse response = new ApiResponse(0, students);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> registerNewStudent(@RequestBody Student student) {
        try {
            studentService.addNewStudent(student);
            // Trả về JSON response thông báo thành công
            String successMessage = "Student đã được tạo thành công.";
            return new ResponseEntity<>(ResponseService.createSuccessResponse("success", successMessage), HttpStatus.OK);
        } catch (IllegalStateException e) {
            // Nếu có lỗi, trả về JSON response thông báo lỗi
            String errorMessage = e.getMessage();
            return new ResponseEntity<>(ResponseService.createErrorResponse("error", errorMessage), HttpStatus.BAD_REQUEST);
        }
    }



}
