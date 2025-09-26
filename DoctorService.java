package com.smartclinic.service;

import com.smartclinic.model.Doctor;
import com.smartclinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // Get all doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Get available doctors at a specific time
    public List<Doctor> getAvailableDoctors(LocalDateTime time) {
        // Example: you'd need a custom query in DoctorRepository for this
        return doctorRepository.findByAvailableTime(time);
    }

    // Get doctor by ID
    public Doctor getDoctorById(Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        return doctor.orElse(null);
    }

    // Add a new doctor
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Update existing doctor
    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        return doctorRepository.findById(id).map(doctor -> {
            doctor.setName(updatedDoctor.getName());
            doctor.setSpeciality(updatedDoctor.getSpeciality());
            doctor.setEmail(updatedDoctor.getEmail());
            doctor.setPhone(updatedDoctor.getPhone());
            doctor.setAvailableTimes(updatedDoctor.getAvailableTimes());
            return doctorRepository.save(doctor);
        }).orElse(null);
    }

    // Delete doctor
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
