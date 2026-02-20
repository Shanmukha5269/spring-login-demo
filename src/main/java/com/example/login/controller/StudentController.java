package com.example.login.controller;

import com.example.login.dto.LoginRequestDto;
import com.example.login.dto.StudentRequestDto;
import com.example.login.dto.StudentResponseDto;
import com.example.login.service.JwtService;
import com.example.login.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService service;
    private final JwtService jwtService;

    public StudentController(StudentService service, JwtService jwtService){
        this.service = service;
        this.jwtService = jwtService;
    }

    @PostMapping("/students")
    public ResponseEntity<String> addStudent(@RequestBody StudentRequestDto dto){
        service.addStudent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student created");
    }

    @GetMapping("/students")
    public List<StudentResponseDto> getAllStudent(){

//        if(!jwtService.isValid(token))
//            throw new RuntimeException("Unauthorized");

        return service.getAllStudent();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto){

        String token = service.login(dto);

        if(!token.isEmpty())
            return ResponseEntity.ok("login successful and token is "  + token);
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid usn or password");
    }
}
