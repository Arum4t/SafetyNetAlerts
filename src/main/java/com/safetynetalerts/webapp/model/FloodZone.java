package com.safetynetalerts.webapp.model;

import lombok.Data;

import java.util.List;

@Data
public class FloodZone {
    private String lastName;
    private String phone;
    private int age;
    private List<String> allergies;
    private List<String> medications;
}
