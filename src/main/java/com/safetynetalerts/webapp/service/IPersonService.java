package com.safetynetalerts.webapp.service;

import com.safetynetalerts.webapp.model.*;

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

    Map<String, List<Child>> listChildAlert (String address) throws IOException;

    List<PersonInfo> getPersonAllInfo (String firstName, String lastName) throws IOException;

    List<PersonFireStationResponse> getPersonInfoByStation(int station) throws IOException;

    List<Person> getPersonByFireStationAddress (int station) throws IOException;


}
