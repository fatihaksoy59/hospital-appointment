package com.fatih.hospitalappointment.model.enums;

import lombok.Getter;

@Getter
public enum DoctorTitle {
    DR("DR."),
    UZM("UZM. DR."),
    OP("OP. DR."),
    DOC("DOÇ. DR."),
    PROF("PROF. DR. ");

    private final String title;
    DoctorTitle(final String doctorTitle) {
        this.title = doctorTitle;
    }
}
