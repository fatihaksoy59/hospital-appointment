package com.fatih.hospitalappointment.controller;

import com.fatih.hospitalappointment.model.entity.Department;
import com.fatih.hospitalappointment.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "department")
public class DepartmentController {

    //TODO Is @ResponseBody must ?
    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public @ResponseBody ResponseEntity<Department> addDepartment(@Valid @RequestBody final Department department) {
        return departmentService.addDepartment(department);
    }

    //TODO validation for only staff
    @GetMapping(path = "getAll")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    //TODO validation for only staff
    @DeleteMapping("{id}")
    public HttpStatus deleteDepartment(@PathVariable final int id) {
        return departmentService.deleteDepartment(id);
    }
}
