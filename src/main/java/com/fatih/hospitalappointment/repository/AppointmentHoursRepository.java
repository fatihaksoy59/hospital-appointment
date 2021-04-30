package com.fatih.hospitalappointment.repository;

import com.fatih.hospitalappointment.model.entity.AppointmentHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentHoursRepository extends JpaRepository<AppointmentHours, Integer> {
}
