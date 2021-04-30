package com.fatih.hospitalappointment.repository;

import com.fatih.hospitalappointment.model.entity.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointments, Integer> {

    Optional<List<Appointments>> findAppointmentsByDoctorIdAndAppointmentDate(int doctorId, String appointmentDate);
}
