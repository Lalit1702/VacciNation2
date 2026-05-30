package com.example.VacciNation.Service;

import com.example.VacciNation.Model.Patient;
import com.example.VacciNation.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public void addPatient(Patient patient) {
        patientRepository.save(patient);

    }
}
