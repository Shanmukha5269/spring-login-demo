package com.example.login.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Student {

    @NotNull
    @Id
    private int usn;

    @NotBlank
    private String name;
    private String password;

    public enum Role{
        ADMIN,
        USER
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
