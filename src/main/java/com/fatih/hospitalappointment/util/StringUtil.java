package com.fatih.hospitalappointment.util;

import com.fatih.hospitalappointment.model.dto.DoctorDto;
import com.fatih.hospitalappointment.model.entity.Doctor;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class StringUtil {

    private final String emptyString = " ";

    public void mergeDoctorTitleNameSurname(final List<Doctor> doctors, final List<DoctorDto> dto) {
        doctors.stream().forEach(doctor -> setDTO(doctor, dto));
    }

    private void setDTO(final Doctor doctor, final List<DoctorDto> dto) {
        final String fullName = doctor.getDoctorTitle().getTitle()
                + emptyString + doctor.getFirstName() + emptyString + doctor.getLastName();

        final DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(doctor.getId());
        doctorDto.setDoctorTitleNameSurname(fullName);

        dto.add(doctorDto);
    }
}
