package com.hospital.hospital_management.repository;

import com.hospital.hospital_management.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findBySpecialization(String specialization);

    List<Doctor> findByNameContaining(String name);

    Optional<Doctor> findByUserId(Long userId);
}