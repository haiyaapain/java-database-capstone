package com.clinic.service;

import com.clinic.model.Doctor;
import com.clinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }


    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public boolean validateLogin(String email, String password) {
        Optional<Doctor> doctorOpt = doctorRepository.findByEmail(email);
        if (doctorOpt.isPresent()) {
            Doctor doctor = doctorOpt.get();
            // For now, plain text comparison (better: hash + salt in real app)
            return doctor.getPassword().equals(password);
        }
        return false;
    }


    public List<LocalTime> getAvailableTimeSlots(Long doctorId, LocalDate date) {
        // Example fixed working hours: 9AM to 5PM
        List<LocalTime> allSlots = new ArrayList<>();
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(17, 0);

        while (start.isBefore(end)) {
            allSlots.add(start);
            start = start.plusMinutes(30); // 30-min slot intervals
        }

        // TODO: Filter out booked slots using AppointmentRepository
        // For now, return all slots
        return allSlots;
    }
}
