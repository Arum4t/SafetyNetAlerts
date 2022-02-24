package com.safetynetalerts.webapp.repository;

import com.safetynetalerts.webapp.model.Person;
import java.util.List;

public interface IPersonRepository {

    List<Person> getPersons();

    Person getPerson(String email);

    Person savePerson(Person person);

    Person updatePerson(Person person);

    Person deletePerson(Person person);

    List<Person> getPersonsByAddress(String address);

    List<String> getEmailByCity (String city);

}
