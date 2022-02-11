package com.safetynetalerts.webapp.model;

import lombok.Data;

import java.util.List;

@Data
public class MedicalRecord {

    private String firstName;
    private String lastName;
    private List<String> allergies;
    private String birthdate;
    private List<String> medications;

}
