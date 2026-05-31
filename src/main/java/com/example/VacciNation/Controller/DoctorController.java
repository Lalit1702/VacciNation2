package com.example.VacciNation.Controller;

import com.example.VacciNation.Model.Doctor;
import com.example.VacciNation.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/doctor")
@RestController
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/add-doctor")
    public String addDoctor(@RequestBody Doctor doctor){
        doctorService.addDoctor(doctor);
        return "Doctor is Added Successfully";
    }

    @GetMapping("/get-doctor")
    public Doctor getDoctor(@RequestParam("id") int id){
        return doctorService.getDoctor(id);
    }
}
