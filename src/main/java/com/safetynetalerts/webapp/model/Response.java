package com.safetynetalerts.webapp.model;

import lombok.Data;

import java.util.List;

@Data
public class Response {
    private List<FireStation> firestations;
    private List<Person> persons;
    private List<MedicalRecord> medicalrecords;

}
