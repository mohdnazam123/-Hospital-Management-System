package com.hospital.hospital_management.dto;

import com.hospital.hospital_management.model.User;
import lombok.Data;

@Data
public class RegisterRequest {  // ADMIN, DOCTOR, PATIENT
    private String name;
    private String email;
    private String password;
    private User.Role role;
    private String phone;
    private String specialization; // sirf doctor ke liye
    private String bloodGroup;     // sirf patient ke liye
}