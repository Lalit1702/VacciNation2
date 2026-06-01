package com.example.VacciNation.Controller;

import com.example.VacciNation.Exception.PatientNotFoundException;
import com.example.VacciNation.Model.Appointment;
import com.example.VacciNation.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/appointment")
@RestController
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/book-appointment")
    public ResponseEntity bookAppointment(@RequestParam("patientid") int patientId, @RequestParam("doctorid") int doctorId){
        try{
            Appointment bookedAppointment = appointmentService.bookAppointment(patientId,doctorId);
            return new ResponseEntity<>(bookedAppointment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }

    }
}
