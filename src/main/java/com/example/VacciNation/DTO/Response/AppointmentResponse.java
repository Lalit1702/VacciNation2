package com.example.VacciNation.DTO.Response;

import com.example.VacciNation.Enum.AppointmentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppointmentResponse {

    private String appointmentID; //UUID generation

    private Date dateOfAppointment;

    private AppointmentStatus appointmentStatus;

    private PatientResponse patientResponse;

    private String doctorName;
}
