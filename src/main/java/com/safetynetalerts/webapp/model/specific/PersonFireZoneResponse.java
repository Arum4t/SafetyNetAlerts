package com.safetynetalerts.webapp.model.specific;

import lombok.Data;

import java.util.List;

@Data
public class PersonFireZoneResponse {
    private int station;
    private List<PersonInfoByFireZone> persons;
}
