package com.hospital.hospital_management.service;

import com.hospital.hospital_management.dto.*;
import com.hospital.hospital_management.model.*;
import com.hospital.hospital_management.repository.*;
import com.hospital.hospital_management.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired private UserRepository userRepo;
    @Autowired private PatientRepository patientRepo;
    @Autowired private DoctorRepository doctorRepo;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest req) {

        // Email already hai?
        if (userRepo.existsByEmail(req.getEmail()))
            throw new RuntimeException("Email already registered!");

        // User banao
        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        // Node mein: bcrypt.hash(password, 10)
        // Spring mein: passwordEncoder.encode()
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(req.getRole());
        userRepo.save(user);

        // Role ke hisaab se Patient ya Doctor bhi banao
        if (req.getRole() == User.Role.PATIENT) {
            Patient patient = new Patient();
            patient.setName(req.getName());
            patient.setEmail(req.getEmail());
            patient.setPhone(req.getPhone());
            patient.setBloodGroup(req.getBloodGroup());
            patient.setUser(user);
            patientRepo.save(patient);
        } else if (req.getRole() == User.Role.DOCTOR) {
            Doctor doctor = new Doctor();
            doctor.setName(req.getName());
            doctor.setEmail(req.getEmail());
            doctor.setPhone(req.getPhone());
            doctor.setSpecialization(req.getSpecialization());
            doctor.setUser(user);
            doctorRepo.save(doctor);
        }

        String token = jwtUtil.generateToken(
                user.getEmail(), user.getRole().name());

        return new AuthResponse(
                token, user.getRole().name(),
                user.getName(), user.getId());
    }

    public AuthResponse login(LoginRequest req) {
        User user = userRepo.findByEmail(req.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Email not found!"));

        // Node mein: bcrypt.compare(password, hash)
        if (!passwordEncoder.matches(
                req.getPassword(), user.getPassword()))
            throw new RuntimeException("Wrong password!");

        String token = jwtUtil.generateToken(
                user.getEmail(), user.getRole().name());

        return new AuthResponse(
                token, user.getRole().name(),
                user.getName(), user.getId());
    }
}