package com.example.VacciNation.Service;

import com.example.VacciNation.DTO.Response.AppointmentResponse;
import com.example.VacciNation.DTO.Response.PatientResponse;
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

    public AppointmentResponse bookAppointment(int patientId, int doctorId) throws PatientNotFoundException {
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

        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setEmailId(patient.getEmailId());
        patientResponse.setName(patient.getName());
        patientResponse.setVaccinated(patient.isVaccinated());


        Appointment appointment = new Appointment();
        appointment.setAppointmentID(String.valueOf(UUID.randomUUID()));
        appointment.setAppointmentStatus(AppointmentStatus.BOOKED);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        Appointment savedAppointment = appointmentRepository.save(appointment);
        AppointmentResponse appointmentResponse = new AppointmentResponse();
        appointmentResponse.setAppointmentID(savedAppointment.getAppointmentID());
        appointmentResponse.setDateOfAppointment(savedAppointment.getDateOfAppointment());
        appointmentResponse.setAppointmentStatus(savedAppointment.getAppointmentStatus());
        appointmentResponse.setPatientResponse(patientResponse);
        appointmentResponse.setDoctorName(savedAppointment.getDoctor().getName());


        return appointmentResponse;

        //convert all API to DTO
        //Improvisation in project
        //get all vaccinated patients above age 30
        //get all unvaccinated MALES/FEMALES
        //change the vaccinated status for all the patients

        //get all the appointments with a particular doctor
        //make API to change the status of appointment
        //get appointment details of a particular patient


    }
}
