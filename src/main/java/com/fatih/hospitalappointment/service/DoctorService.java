package com.fatih.hospitalappointment.service;

import com.fatih.hospitalappointment.config.exceptions.RecordAlreadyExistException;
import com.fatih.hospitalappointment.config.exceptions.RecordNotFoundException;
import com.fatih.hospitalappointment.model.dto.DoctorDto;
import com.fatih.hospitalappointment.model.entity.AppointmentHours;
import com.fatih.hospitalappointment.model.entity.Appointments;
import com.fatih.hospitalappointment.model.entity.Department;
import com.fatih.hospitalappointment.model.entity.Doctor;
import com.fatih.hospitalappointment.model.enums.DoctorTitle;
import com.fatih.hospitalappointment.repository.AppointmentHoursRepository;
import com.fatih.hospitalappointment.repository.AppointmentsRepository;
import com.fatih.hospitalappointment.repository.DepartmentRepository;
import com.fatih.hospitalappointment.repository.DoctorRepository;
import com.fatih.hospitalappointment.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private AppointmentHoursRepository appointmentHoursRepository;

    @Autowired
    private AppointmentsRepository appointmentsRepository;

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

    public ResponseEntity<List<DoctorDto>> getDoctorsByDepartmentId(final int id) {
        final Department department = new Department(id, null);
        final Optional<List<Doctor>> doctors = doctorRepository.findByDepartment(department);

        final List<DoctorDto> doctorDtos = new ArrayList<DoctorDto>();

        StringUtil.mergeDoctorTitleNameSurname(doctorRepository.findByDepartment(department).get(), doctorDtos);

        return  new ResponseEntity<List<DoctorDto>>(doctorDtos, HttpStatus.OK);

    }

    public ResponseEntity<List<DoctorTitle>> getDoctorTitles() {
        //TODO write enum return
        return null;
    }

    public ResponseEntity<List<AppointmentHours>> getDoctorEmptyHours(final int doctorId, final String date) {
        final List<AppointmentHours> appointmentHoursList = appointmentHoursRepository.findAll();
        final Optional<List<Appointments>> appointments = appointmentsRepository.
                findAppointmentsByDoctorIdAndAppointmentDate(doctorId, date);

        final int size = appointments.map(List::size).orElse(0);
        for (int i = 0; i < size; i++) {
            appointmentHoursList.remove(appointments.get().get(i).getId() - 1);
        }
        return new ResponseEntity<List<AppointmentHours>>(appointmentHoursList, HttpStatus.OK);
    }
}
