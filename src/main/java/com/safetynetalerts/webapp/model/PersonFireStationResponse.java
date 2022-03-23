package com.safetynetalerts.webapp.model;

import com.safetynetalerts.webapp.model.DTO.PersonInfoByStation;
import lombok.Data;

import java.util.List;

@Data
public class PersonFireStationResponse {

    private List<PersonInfoByStation> persons;
    private int child;
    private int adult;
}
