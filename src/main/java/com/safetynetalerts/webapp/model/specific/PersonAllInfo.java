package com.safetynetalerts.webapp.model.specific;

import lombok.Data;

import java.util.List;

@Data
public class PersonAllInfo {
    private String firstName;
    private String lastName;
    private String address;
    private int age;
    private String email;
    private List<String> allergies;
    private List<String> medications;
}
