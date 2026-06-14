package com.hospital.hospital_management.controller;

import com.hospital.hospital_management.dto.AppointmentRequest;
import com.hospital.hospital_management.model.Appointment;
import com.hospital.hospital_management.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    @Autowired
    private AppointmentService apptService;

    // Appointment book karo
    @PostMapping
    public ResponseEntity<Appointment> book(
            @RequestBody AppointmentRequest req) {
        return ResponseEntity.ok(
                apptService.bookAppointment(req));
    }


    @GetMapping("/patient/{id}")
    public ResponseEntity<List<Appointment>> byPatient(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                apptService.getByPatient(id));
    }

    // Doctor
    @GetMapping("/doctor/{id}")
    public ResponseEntity<List<Appointment>> byDoctor(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                apptService.getByDoctor(id));
    }

    // Status update
    @PutMapping("/{id}/status")
    public ResponseEntity<Appointment> updateStatus(
            @PathVariable Long id,
            @RequestParam Appointment.Status status) {
        return ResponseEntity.ok(
                apptService.updateStatus(id, status));
    }
}