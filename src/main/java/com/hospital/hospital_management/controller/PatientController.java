package com.hospital.hospital_management.controller;

import com.hospital.hospital_management.model.Patient;
import com.hospital.hospital_management.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // Sirf ADMIN
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Patient>> getAll() {
        return ResponseEntity.ok(
                patientService.getAllPatients());
    }

    // Doctor aur Admin patient dekh sakte hain
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    public ResponseEntity<Patient> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                patientService.getPatientById(id));
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    public ResponseEntity<List<Patient>> search(
            @RequestParam String name) {
        return ResponseEntity.ok(
                patientService.searchPatients(name));
    }
}