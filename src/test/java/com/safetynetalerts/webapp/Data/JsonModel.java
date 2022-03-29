package com.safetynetalerts.webapp.Data;

import com.safetynetalerts.webapp.model.DTO.PersonAllInfo;
import com.safetynetalerts.webapp.model.DTO.PersonInfoByFloodZone;
import com.safetynetalerts.webapp.model.Person;
import com.safetynetalerts.webapp.model.PersonFireStationResponse;
import com.safetynetalerts.webapp.model.PersonFireZoneResponse;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class JsonModel {
    private List<PersonAllInfo> fullInfo;

    private List<PersonFireStationResponse> personInfoByFireStation;

    private List<Person> personsByFireStation;

    private List<String> phoneAlert;

    private List <PersonFireZoneResponse> fireZones;

    private Map<String, List<PersonInfoByFloodZone>> floodZones;
}
