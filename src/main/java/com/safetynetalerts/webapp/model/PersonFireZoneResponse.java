package com.safetynetalerts.webapp.model;

import com.safetynetalerts.webapp.model.DTO.PersonInfoByFireZone;
import lombok.Data;

import java.util.List;

@Data
public class PersonFireZoneResponse {
    private int station;
    private List<PersonInfoByFireZone> persons;
}
