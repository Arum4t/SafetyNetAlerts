package com.safetynetalerts.webapp.repository;

import com.safetynetalerts.webapp.model.Person;
import java.util.List;

public interface IPersonRepository {

    List<Person> getPersons();

    Person getPerson(String email);

    Person savePerson(Person person);

    Person updatePerson(String lastName, String firstName);

    Person deletePerson(String email);

}
