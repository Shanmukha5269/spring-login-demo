package com.example.login.service;

import com.example.login.dto.StudentRequestDto;
import com.example.login.dto.StudentResponseDto;
import com.example.login.model.Student;
import com.example.login.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo){
        this.repo = repo;
    }

    public void addStudent(@RequestBody StudentRequestDto dto){
        Student std = new Student();

        std.setUsn(dto.getUsn());
        std.setName(dto.getName());
        std.setPassword(dto.getPassword());
        repo.save(std);
    }

    public List<StudentResponseDto> getAllStudent(){

        List<StudentResponseDto> studentResponseList;

        studentResponseList = repo.findAll().stream()
                .map(std -> {
                    StudentResponseDto dto = new StudentResponseDto();
                    dto.setName(std.getName());
                    dto.setUsn(std.getUsn());
                    return dto;
                }).toList();

        return studentResponseList;
    }
}
