package com.clinic.repository;

import com.clinic.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // You can add custom queries here if needed, for example:
    // List<Doctor> findBySpeciality(String speciality);
}
