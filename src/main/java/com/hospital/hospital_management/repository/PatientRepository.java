package com.hospital.hospital_management.repository;

import com.hospital.hospital_management.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByEmail(String email);


    // Spring mein: Containing = LIKE %keyword%
    List<Patient> findByNameContaining(String name);

    Optional<Patient> findByUserId(Long userId);
}