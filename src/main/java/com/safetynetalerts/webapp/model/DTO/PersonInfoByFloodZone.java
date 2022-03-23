package com.safetynetalerts.webapp.model.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PersonInfoByFloodZone {
    private String lastName;
    private String phone;
    private int age;
    private List<String> allergies;
    private List<String> medications;
}
