package com.example.VacciNation.Service;

import com.example.VacciNation.Enum.AppointmentStatus;
import com.example.VacciNation.Exception.DoctorNotFoundException;
import com.example.VacciNation.Exception.PatientNotFoundException;
import com.example.VacciNation.Model.Appointment;
import com.example.VacciNation.Model.Doctor;
import com.example.VacciNation.Model.Patient;
import com.example.VacciNation.Repository.AppointmentRepository;
import com.example.VacciNation.Repository.DoctorRepository;
import com.example.VacciNation.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    public Appointment bookAppointment(int patientId, int doctorId) throws PatientNotFoundException {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);

        if(optionalDoctor.isEmpty()){
            throw new DoctorNotFoundException("Doctor is not available with the given Id");
        }

        Doctor doctor = optionalDoctor.get();

        Optional<Patient> optionalPatient = patientRepository.findById(patientId);

        if(optionalPatient.isEmpty()){
            throw new PatientNotFoundException("Patient is not available with the given Id");
        }

        Patient patient = optionalPatient.get();

        Appointment appointment = new Appointment();
        appointment.setAppointmentID(String.valueOf(UUID.randomUUID()));
        appointment.setAppointmentStatus(AppointmentStatus.BOOKED);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        return appointmentRepository.save(appointment);





    }
}
