package com.example.VacciNation.Controller;

import com.example.VacciNation.Model.Patient;
import com.example.VacciNation.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/patient")
@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping("/add-patient")
    public String addPatient(@RequestBody Patient patient){
        patientService.addPatient(patient);
        return  "Patient Added Successfully";
    }
}
