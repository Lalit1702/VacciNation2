package com.example.VacciNation.Controller;

import com.example.VacciNation.Enum.VaccineBrand;
import com.example.VacciNation.Exception.PatientNotFoundException;
import com.example.VacciNation.Model.Dose;
import com.example.VacciNation.Service.DoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dose")
public class DoseController {

    @Autowired
    DoseService doseService;

    @PostMapping("/vaccinate")
    public Dose addDose(@RequestParam("id") int patientId, @RequestParam("brand") VaccineBrand vaccineBrand) throws PatientNotFoundException {
        return doseService.addDose(patientId,vaccineBrand);
    }
}
