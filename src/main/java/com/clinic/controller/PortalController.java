package com.clinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortalController {

    @GetMapping("/admin")
    public String adminLoginPage() {
        return "admin-login";
    }

    @GetMapping("/doctor")
    public String doctorLoginPage() {
        return "doctor-login";
    }

    @GetMapping("/patient")
    public String patientLoginPage() {
        return "patient-login";
    }

    @GetMapping("/admin/dashboard")
public String adminDashboard() {
    return "admin-dashboard";
}

@GetMapping("/doctor/dashboard")
public String doctorDashboard() {
    return "doctor-dashboard";
}

@GetMapping("/patient/dashboard")
public String patientDashboard() {
    return "patient-dashboard";
}
}
