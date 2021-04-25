package com.fatih.hospitalappointment.service;

import com.fatih.hospitalappointment.config.exceptions.RecordAlreadyExistException;
import com.fatih.hospitalappointment.config.exceptions.RecordNotFoundException;
import com.fatih.hospitalappointment.model.entity.Department;
import com.fatih.hospitalappointment.model.entity.Doctor;
import com.fatih.hospitalappointment.repository.DepartmentRepository;
import com.fatih.hospitalappointment.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public ResponseEntity<Doctor> addDoctor(final Doctor doctor) {
        doctorRepository.findByNationalIdentityNo(doctor.getNationalIdentityNo())
                .ifPresent(ex -> {
                    throw new RecordAlreadyExistException(doctor.getNationalIdentityNo()); });

        final Optional<Department> department = departmentRepository.findById(doctor.getDepartment().get(0).getId());

        if (!department.isPresent()) {
            throw  new RecordNotFoundException("Department id: " + doctor.getDepartment().get(0).getId());
        }

        return new ResponseEntity<Doctor>(doctorRepository.save(doctor), HttpStatus.OK);
    }

    public ResponseEntity<Doctor> getDoctorByNationalIdentityNo(final String nationalIdentityNo) {
        final Optional<Doctor> doctor = doctorRepository.findByNationalIdentityNo(nationalIdentityNo);
        if (!doctor.isPresent()) {
            throw new RecordNotFoundException("" + nationalIdentityNo);
        }
        return new ResponseEntity<Doctor>(doctor.get(), HttpStatus.OK);
    }

    public ResponseEntity<List<Doctor>> getDoctorsByDepartmentId(final int id) {
        final Department department = new Department(id, null);
        final Optional<List<Doctor>> doctors = doctorRepository.findByDepartment(department);

        return  new ResponseEntity<List<Doctor>>(doctorRepository.findByDepartment(department).get(), HttpStatus.OK);
    }
}