package com.example.VacciNation.Controller;

import com.example.VacciNation.Exception.PatientNotFoundException;
import com.example.VacciNation.Model.Patient;
import com.example.VacciNation.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/get-patient")
    public Patient getPatient(@RequestParam("id")  int id) throws PatientNotFoundException {
        return patientService.getPatient(id);
    }
}
