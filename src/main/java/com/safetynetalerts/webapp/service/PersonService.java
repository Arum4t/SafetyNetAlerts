package com.safetynetalerts.webapp.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.webapp.model.Person;
import com.safetynetalerts.webapp.repository.DataLoaderRepository;
import com.safetynetalerts.webapp.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class PersonService {

private DataLoaderRepository dataLoaderRepository;
private PersonRepository personRepository;

    public PersonService() throws IOException {
        this.dataLoaderRepository = new DataLoaderRepository(new ObjectMapper());

        this.personRepository = new PersonRepository(new ArrayList<>(this.dataLoaderRepository.getResponse().getPersons()));
    }

    public List<Person> getAllPersons() throws IOException {
    return this.personRepository.getPersons();
}

}
