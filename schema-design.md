# Smart Clinic Management System - Database Schema

## Tables

### 1. Doctor
- **doctor_id** (INT, PK, AUTO_INCREMENT)
- **name** (VARCHAR(100), NOT NULL)
- **speciality** (VARCHAR(100), NOT NULL)
- **email** (VARCHAR(150), UNIQUE, NOT NULL)
- **phone** (VARCHAR(20), UNIQUE, NOT NULL)
- **available_times** (JSON, NOT NULL)

---

### 2. Patient
- **patient_id** (INT, PK, AUTO_INCREMENT)
- **name** (VARCHAR(100), NOT NULL)
- **email** (VARCHAR(150), UNIQUE, NOT NULL)
- **phone** (VARCHAR(20), UNIQUE, NOT NULL)
- **dob** (DATE, NOT NULL)

---

### 3. Appointment
- **appointment_id** (INT, PK, AUTO_INCREMENT)
- **doctor_id** (INT, FK → Doctor.doctor_id, NOT NULL)
- **patient_id** (INT, FK → Patient.patient_id, NOT NULL)
- **appointment_time** (DATETIME, NOT NULL)
- **status** (ENUM('BOOKED', 'CANCELLED', 'COMPLETED'), DEFAULT 'BOOKED')

---

### 4. Prescription
- **prescription_id** (INT, PK, AUTO_INCREMENT)
- **appointment_id** (INT, FK → Appointment.appointment_id, NOT NULL)
- **doctor_id** (INT, FK → Doctor.doctor_id, NOT NULL)
- **patient_id** (INT, FK → Patient.patient_id, NOT NULL)
- **medicine** (VARCHAR(255), NOT NULL)
- **dosage** (VARCHAR(100), NOT NULL)
- **instructions** (TEXT)

---

## Relationships
- A **Doctor** can have many **Appointments**.  
- A **Patient** can book many **Appointments**.  
- Each **Appointment** can have **one Prescription**.  
- **Prescription** links Doctor, Patient, and Appointment.

