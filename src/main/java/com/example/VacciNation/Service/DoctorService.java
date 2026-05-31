package com.example.VacciNation.Service;

import com.example.VacciNation.Exception.DoctorNotFoundException;
import com.example.VacciNation.Model.Doctor;
import com.example.VacciNation.Model.Patient;
import com.example.VacciNation.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    public void addDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public Doctor getDoctor(int id) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if(optionalDoctor.isEmpty()){
            throw new DoctorNotFoundException("Doctor is not found for the given Id");
        }
        Doctor doctor = optionalDoctor.get();
        return doctor;
    }
}
