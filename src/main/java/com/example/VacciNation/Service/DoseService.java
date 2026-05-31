package com.example.VacciNation.Service;

import com.example.VacciNation.Enum.VaccineBrand;
import com.example.VacciNation.Exception.DoseAlreadyTakenException;
import com.example.VacciNation.Exception.PatientNotFoundException;
import com.example.VacciNation.Model.Dose;
import com.example.VacciNation.Model.Patient;
import com.example.VacciNation.Repository.DoseRepository;
import com.example.VacciNation.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DoseService {

    @Autowired
    DoseRepository doseRepository;

    @Autowired
    PatientRepository patientRepository;


    public Dose addDose(int patientId, VaccineBrand vaccineBrand) throws PatientNotFoundException {
        //1. need to check whether the patient Id is valid or not
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if(optionalPatient.isEmpty()){
            throw new PatientNotFoundException("Invalid Patient ID");
        }

        Patient patient = optionalPatient.get();

        if(patient.isVaccinated()){
            throw new DoseAlreadyTakenException("Dose is Already Taken for the given Patient");
        }
        patient.setVaccinated(true);

        Dose dose = new Dose();
        dose.setVaccineBrand(vaccineBrand);
        dose.setSerialNUmber(String.valueOf(UUID.randomUUID()));
        dose.setPatient(patient); //setting the foreign key

        patientRepository.save(patient);
        doseRepository.save(dose);
        return dose;


    }
}
