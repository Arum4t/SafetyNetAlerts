package com.safetynetalerts.webapp.service;

import com.safetynetalerts.webapp.model.*;
import com.safetynetalerts.webapp.model.ChildAlert;
import com.safetynetalerts.webapp.model.DTO.PersonAllInfo;
import com.safetynetalerts.webapp.model.DTO.PersonInfoByFloodZone;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IPersonService {

    List<Person> getAll();

    Person getPerson(String email);

    Person savePerson(Person person);

    Person updatePerson(Person person);

    Boolean deletePerson(String firstName, String lastName);

    List<Person> getPersonsByAddress(String address);

    List<String> getEmailByCity (String city);

    Map<String, List<ChildAlert>> listChildAlert (String address) throws IOException;

    List<PersonAllInfo> getPersonAllInfo (String firstName, String lastName) throws IOException;

    List<PersonFireStationResponse> getPersonsInfoByStation (int station) throws IOException;

    List<Person> getPersonByFireStation (int station) throws IOException;

    List<String> getPhoneAlert (int fireStationNumber) throws IOException;

    List<PersonFireZoneResponse> getFireZone (String address) throws IOException;

    Map<String, List<PersonInfoByFloodZone>> getFloodZone(List<Integer> stations) throws IOException;


}
