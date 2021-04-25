package com.fatih.hospitalappointment.service;

import com.fatih.hospitalappointment.config.exceptions.RecordAlreadyExistException;
import com.fatih.hospitalappointment.config.exceptions.RecordNotFoundException;
import com.fatih.hospitalappointment.model.entity.Department;
import com.fatih.hospitalappointment.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public ResponseEntity<Department> addDepartment(final Department department) {
        departmentRepository.findByDepartmentName(department.getDepartmentName())
                .ifPresent(ex -> {
                    throw new RecordAlreadyExistException(department.getDepartmentName()); });

        return new ResponseEntity<Department>(
                departmentRepository.save(department), HttpStatus.OK);
    }

    public ResponseEntity<List<Department>> getAllDepartments() {
        return new ResponseEntity<List<Department>>(departmentRepository.findAll(), HttpStatus.OK);
    }

    public HttpStatus deleteDepartment(final int id) {
        final Optional<Department> department = departmentRepository.findById(id);
        if (!department.isPresent()) {
            throw new RecordNotFoundException("" + id);
        }
        departmentRepository.deleteById(id);
        return HttpStatus.OK;
    }
}
