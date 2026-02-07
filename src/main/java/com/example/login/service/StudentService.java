package com.example.login.service;

import com.example.login.dto.LoginRequestDto;
import com.example.login.dto.StudentRequestDto;
import com.example.login.dto.StudentResponseDto;
import com.example.login.model.Student;
import com.example.login.repository.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repo;
    private final PasswordEncoder encoder;
    private final TokenService tokenService;

    public StudentService(StudentRepository repo, PasswordEncoder encoder, TokenService tokenService){
        this.repo = repo;
        this.encoder = encoder;
        this.tokenService = tokenService;
    }

    public void addStudent(@RequestBody StudentRequestDto dto){
        Student std = new Student();

        std.setUsn(dto.getUsn());
        std.setName(dto.getName());
        std.setPassword(encoder.encode(dto.getPassword()));
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

    public boolean login(LoginRequestDto dto){

        Optional<Student> optionalstd = repo.findByUsn(dto.getUsn());

        if(optionalstd.isEmpty())
                return false;

        Student std = optionalstd.get();

        return encoder.matches(dto.getPassword(), std.getPassword());
    }

    public String loginToken(LoginRequestDto dto){

        Optional<Student> optionalstd = repo.findByUsn(dto.getUsn());

        if(optionalstd.isEmpty())
            return "User not found";

        Student std = optionalstd.get();

        if(!encoder.matches(dto.getPassword(),std.getPassword()))
            return "Invalid password";

        String token = tokenService.generateToken(std.getName());

        return token;
    }
}
