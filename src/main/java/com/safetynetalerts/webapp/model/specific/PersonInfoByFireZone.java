package com.safetynetalerts.webapp.model.specific;

import lombok.Data;

import java.util.List;

@Data
public class PersonInfoByFireZone {
    private String lastName;
    private int age;
    private String phone;
    private List<String> allergies;
    private List<String> medications;
}
