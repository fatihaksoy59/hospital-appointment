package com.fatih.hospitalappointment.model.entity;

import com.fatih.hospitalappointment.model.enums.DoctorTitle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    //TODO make table sorting like model

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private String nationalIdentityNo;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String birthDate;
    @Email(regexp = "^(.+)@(.+)$", message = "Email is not valid")
    private String email;
    @NotEmpty
    private String phone;

    @NotNull
    private DoctorTitle doctorTitle;

    //TODO bunu dizi olarak yazmak zorunda mıyım ? Tek department id ile ilerleyemez miyim ?
    @NotNull
    @ManyToMany
    private List<Department> department;
}
