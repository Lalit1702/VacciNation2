package com.example.VacciNation.Service;

import com.example.VacciNation.DTO.Request.PatientRequest;
import com.example.VacciNation.DTO.Response.PatientResponse;
import com.example.VacciNation.Enum.Gender;
import com.example.VacciNation.Exception.PatientNotFoundException;
import com.example.VacciNation.Model.Patient;
import com.example.VacciNation.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public PatientResponse addPatient(PatientRequest patientRequest) {
        //1.request DTO -> model/entity
        Patient patient = new Patient();
        patient.setVaccinated(false);
        patient.setName(patientRequest.getName());
        patient.setAge(patientRequest.getAge());
        patient.setEmailId(patientRequest.getEmailId());
        patient.setGender(patientRequest.getGender());
        Patient savedPatient = patientRepository.save(patient);
        //2. model/entity -> response DTO
        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setName(savedPatient.getName());
        patientResponse.setVaccinated(savedPatient.isVaccinated());
        patientResponse.setEmailId(savedPatient.getEmailId());

        return patientResponse;

    }

    public PatientResponse getPatient(int id) throws PatientNotFoundException {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if(patientOptional.isEmpty()){
            throw new PatientNotFoundException("Patient with the given id is not Available");
        }
        Patient savedPatient = patientOptional.get();
        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setName(savedPatient.getName());
        patientResponse.setVaccinated(savedPatient.isVaccinated());
        patientResponse.setEmailId(savedPatient.getEmailId());
        return patientResponse;
    }

    public List<PatientResponse> getAllPatientsByGender(Gender gender) {
        List<Patient> allPatients = patientRepository.findAll();
        List<PatientResponse> patientResponses = new ArrayList<>();
        for(Patient patient: allPatients){
            if(patient.getGender()==gender) {
                PatientResponse patientResponse = new PatientResponse();
                patientResponse.setName(patient.getName());
                patientResponse.setVaccinated(patient.isVaccinated());
                patientResponse.setEmailId(patient.getEmailId());

                patientResponses.add(patientResponse);
            }
        }
        return patientResponses;
    }
}
