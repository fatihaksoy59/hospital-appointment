package com.fatih.hospitalappointment.controller;

import com.fatih.hospitalappointment.model.entity.Doctor;
import com.fatih.hospitalappointment.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    private ResponseEntity<Doctor> addDoctor(@Valid @RequestBody final Doctor doctor) {
        return doctorService.addDoctor(doctor);
    }

    @GetMapping({"{nationalIdentityNo}"})
    private ResponseEntity<Doctor> getDoctorByNationalIdentityNo(@PathVariable final String nationalIdentityNo) {
        return doctorService.getDoctorByNationalIdentityNo(nationalIdentityNo);
    }

    @GetMapping(path = "/getByDepartment/{id}")
    private ResponseEntity<List<Doctor>> getDoctorsByDepartmentId(@PathVariable final int id) {
        return doctorService.getDoctorsByDepartmentId(id);
    }
}
