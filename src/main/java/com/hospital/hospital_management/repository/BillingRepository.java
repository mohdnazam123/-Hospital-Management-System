package com.hospital.hospital_management.repository;

import com.hospital.hospital_management.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BillingRepository extends JpaRepository<Billing, Long> {

    List<Billing> findByPatientId(Long patientId);

    java.util.Optional<Billing> findByAppointmentId(Long appointmentId);
}