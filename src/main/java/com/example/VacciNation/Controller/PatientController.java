package com.example.VacciNation.Controller;

import com.example.VacciNation.DTO.Request.PatientRequest;
import com.example.VacciNation.DTO.Response.PatientResponse;
import com.example.VacciNation.Enum.Gender;
import com.example.VacciNation.Exception.PatientNotFoundException;
import com.example.VacciNation.Model.Patient;
import com.example.VacciNation.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/patient")
@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping("/add-patient")
    public ResponseEntity addPatient(@RequestBody PatientRequest patientRequest){
        try {
            PatientResponse patientResponse = patientService.addPatient(patientRequest);
            return new ResponseEntity<>(patientResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/get-patient")
    public PatientResponse getPatient(@RequestParam("id")  int id) throws PatientNotFoundException {
        return patientService.getPatient(id);
    }

    @GetMapping("/get-patients-genderwise/{gender}")
    public List<PatientResponse> getAllPatientsByGender(@PathVariable("gender")Gender gender){
        return patientService.getAllPatientsByGender(gender);
    }
}
