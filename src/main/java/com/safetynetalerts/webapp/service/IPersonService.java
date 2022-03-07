package com.safetynetalerts.webapp.service;

import com.safetynetalerts.webapp.model.Child;
import com.safetynetalerts.webapp.model.MedicalRecord;
import com.safetynetalerts.webapp.model.Person;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IPersonService {

    List<Person> getAllPersons();

    Person getPerson(String email);

    Person savePerson(Person person);

    Person updatePerson(Person person);

    Boolean deletePerson(String email);

    List<Person> getPersonsByAddress(String address);

    List<String> getEmailByCity (String city);

    Map<String, List<Child>> listChildAlert ();

    MedicalRecord getPersonMedicalRecords (String firstName, String lastName) throws IOException;


}