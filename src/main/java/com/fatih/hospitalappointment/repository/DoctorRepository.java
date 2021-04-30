package com.fatih.hospitalappointment.repository;

import com.fatih.hospitalappointment.model.entity.Department;
import com.fatih.hospitalappointment.model.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    Optional<Doctor> findByNationalIdentityNo(String nationalIdentityNo);

    Optional<List<Doctor>> findByDepartment(Department department);
}
