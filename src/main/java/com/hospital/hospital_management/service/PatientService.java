package com.hospital.hospital_management.service;

import com.hospital.hospital_management.model.Patient;
import com.hospital.hospital_management.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepo;

    // Node mein: Patient.find()
    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    // Node mein: Patient.findById(id)
    public Patient getPatientById(Long id) {
        return patientRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Patient not found!"));
    }

    public List<Patient> searchPatients(String name) {
        return patientRepo.findByNameContaining(name);
    }

    public Patient updatePatient(Long id, Patient updated) {
        Patient existing = getPatientById(id);
        existing.setName(updated.getName());
        existing.setPhone(updated.getPhone());
        existing.setAddress(updated.getAddress());
        existing.setBloodGroup(updated.getBloodGroup());
        return patientRepo.save(existing);
    }
}