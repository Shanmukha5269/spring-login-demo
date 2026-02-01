package com.example.login.controller;

import com.example.login.dto.StudentRequestDto;
import com.example.login.dto.StudentResponseDto;
import com.example.login.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service){
        this.service = service;
    }

    @PostMapping("/students")
    public ResponseEntity<String> addStudent(@RequestBody StudentRequestDto dto){
        service.addStudent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student created");
    }

    @GetMapping("/students")
    public List<StudentResponseDto> getAllStudent(){
        return service.getAllStudent();
    }
}
