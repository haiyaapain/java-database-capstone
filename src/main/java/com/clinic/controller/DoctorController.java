package com.clinic.controller;

import com.clinic.model.Doctor;
import com.clinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return doctorService.saveDoctor(doctor);
    }

    @GetMapping("/{doctorId}/availability")
    public ResponseEntity<?> getDoctorAvailability(
            @PathVariable Long doctorId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("role") String role,
            @RequestParam("token") String token) {

        if (token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Token is required"));
        }

        if (!role.equalsIgnoreCase("admin") &&
            !role.equalsIgnoreCase("doctor") &&
            !role.equalsIgnoreCase("patient")) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid role"));
        }

        List<String> availableSlots = doctorService.getAvailability(doctorId, date);

        if (availableSlots.isEmpty()) {
            return ResponseEntity.ok(Map.of(
                    "doctorId", doctorId,
                    "date", date,
                    "availability", "No slots available"
            ));
        }

        return ResponseEntity.ok(Map.of(
                "doctorId", doctorId,
                "date", date,
                "availability", availableSlots
        ));
    }
}
