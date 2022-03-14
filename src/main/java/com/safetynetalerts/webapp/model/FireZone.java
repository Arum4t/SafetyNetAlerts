package com.safetynetalerts.webapp.model;

import lombok.Data;

import java.util.List;

@Data
public class FireZone {
    private String lastName;
    private int age;
    private String phone;
    private List<String> allergies;
    private List<String> medications;
}
