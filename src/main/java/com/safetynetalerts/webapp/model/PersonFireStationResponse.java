package com.safetynetalerts.webapp.model;

import lombok.Data;

import java.util.List;

@Data
public class PersonFireStationResponse {

    private List<PersonInfoByStation> persons;
    private int child;
    private int adult;
}
