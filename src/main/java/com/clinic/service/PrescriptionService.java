package com.clinic.service;

import com.clinic.model.Prescription;
import com.clinic.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public Prescription addPrescription(String token, Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }
}
