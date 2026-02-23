package com.example.login.dto;

import com.example.login.model.Student;

public class StudentRequestDto {

    private int usn;
    private String name;
    private String password;
    private Student.Role role;

    public int getUsn() {
        return usn;
    }

    public void setUsn(int usn) {
        this.usn = usn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Student.Role getRole() {
        return role;
    }

    public void setRole(Student.Role role) {
        this.role = role;
    }
}
