# Smart Clinic Management System Database Design

## Tables

### Doctor
- id (PK)
- name
- specialization
- email

### Patient
- id (PK)
- name
- email
- phone

### Appointment
- id (PK)
- doctor_id (FK -> Doctor.id)
- patient_id (FK -> Patient.id)
- appointmentTime

### Prescription
- id (PK)
- appointment_id (FK -> Appointment.id)
- medication
- dosage

## Relationships
- One Doctor has many Appointments
- One Patient has many Appointments
- One Appointment has one Prescription
