package com.safetynetalerts.webapp.service;

import com.safetynetalerts.webapp.model.Person;

import java.util.ArrayList;
import java.util.List;

public interface IPersonService {

    List<Person> getAllPersons();

    Person getPerson(String email);

    Person savePerson(Person person);

    Person updatePerson();

    boolean deletePerson();

}
