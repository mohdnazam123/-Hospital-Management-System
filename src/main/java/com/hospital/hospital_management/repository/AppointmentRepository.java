package com.hospital.hospital_management.repository;

import com.hospital.hospital_management.model.Appointment;
import com.hospital.hospital_management.model.Appointment.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByDoctorId(Long doctorId);

    List<Appointment> findByStatus(Status status);


    @Query("SELECT COUNT(a) > 0 FROM Appointment a " +
            "WHERE a.doctor.id = :doctorId " +
            "AND a.appointmentDate = :date " +
            "AND a.appointmentTime = :time " +
            "AND a.status != 'CANCELLED'")
    boolean isDoctorBooked(Long doctorId, LocalDate date,
                           java.time.LocalTime time);
}