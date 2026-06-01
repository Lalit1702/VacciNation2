package com.example.VacciNation.Controller;

import com.example.VacciNation.Exception.PatientNotFoundException;
import com.example.VacciNation.Model.Patient;
import com.example.VacciNation.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/patient")
@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping("/add-patient")
    public ResponseEntity addPatient(@RequestBody Patient patient){
        try {
            patientService.addPatient(patient);
            return new ResponseEntity<>(patient, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/get-patient")
    public Patient getPatient(@RequestParam("id")  int id) throws PatientNotFoundException {
        return patientService.getPatient(id);
    }
}
