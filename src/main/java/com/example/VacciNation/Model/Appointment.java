package com.example.VacciNation.Model;

import com.example.VacciNation.Enum.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String appointmentID;

    private Date dateOfAppointment;

    @Enumerated(value = EnumType.STRING)
    private AppointmentStatus appointmentStatus;


    @ManyToOne
    @JoinColumn(name = "doctor_id")
    Doctor doctor;

    @OneToOne
    @JoinColumn(name = "patient_id")
    Patient patient;


}
