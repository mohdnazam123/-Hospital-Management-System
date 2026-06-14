package com.hospital.hospital_management.dto;

import lombok.Data;


// Spring mein: yeh object automatically req.body se banta hai
@Data
public class LoginRequest {
    private String email;
    private String password;
}