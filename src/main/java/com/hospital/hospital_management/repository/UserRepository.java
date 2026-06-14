package com.hospital.hospital_management.repository;

import com.hospital.hospital_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {

    java.util.Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}