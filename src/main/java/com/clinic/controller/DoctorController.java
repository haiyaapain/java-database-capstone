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

    // ✅ Existing endpoints
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return doctorService.saveDoctor(doctor);
    }

    // ✅ NEW endpoint: retrieve doctor availability
    @GetMapping("/{doctorId}/availability")
    public ResponseEntity<?> getDoctorAvailability(
            @PathVariable Long doctorId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("role") String role,
            @RequestParam("token") String token) {

        // 🚩 (1) Basic role-based check (could later connect to JWT/security)
        if (token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Token is required"));
        }

        if (!role.equalsIgnoreCase("admin") &&
            !role.equalsIgnoreCase("doctor") &&
            !role.equalsIgnoreCase("patient")) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid role"));
        }

        // 🚩 (2) Fetch availability from service
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
