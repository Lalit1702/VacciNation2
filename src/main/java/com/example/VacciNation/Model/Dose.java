package com.example.VacciNation.Model;

import com.example.VacciNation.Enum.VaccineBrand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Dose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private VaccineBrand vaccineBrand;

    private String SerialNUmber; //UUID




    @CreationTimestamp
    private Date dateOfVaccination;

    @OneToOne
    @JoinColumn(name = "patient_id")
    Patient patient;
}
