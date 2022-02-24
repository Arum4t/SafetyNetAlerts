package com.safetynetalerts.webapp.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.webapp.controller.LoggingController;
import com.safetynetalerts.webapp.model.Person;
import com.safetynetalerts.webapp.repository.DataLoaderRepository;
import com.safetynetalerts.webapp.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@Service
public class PersonService implements IPersonService {

    private static final Logger log = LoggerFactory.getLogger(LoggingController.class);

    private DataLoaderRepository dataLoaderRepository;
    private PersonRepository personRepository;
    private ArrayList<Person> persons;

    public PersonService() throws IOException {
        this.dataLoaderRepository = new DataLoaderRepository(new ObjectMapper());
        this.personRepository = new PersonRepository(new ArrayList<>(this.dataLoaderRepository.getResponse().getPersons()));
    }

    @Override
    public List<Person> getAllPersons() {
        return this.personRepository.getPersons();
    }

    @Override
    public Person getPerson(String email) {
        return this.personRepository.getPerson(email);
    }

    @Override
    public Person savePerson(Person person) {
        if (getPerson(person.getEmail()) != null) {
           /*
           throw new ResponseStatusException(
                  HttpStatus.NOT_FOUND, "entity not found"
            );*/
            log.error("404, entity not found");
        }
            return this.personRepository.savePerson(person);
        }


    @Override
    public Person updatePerson(Person person) {
        // tricky, toujours en partant de l'email. Mettre à jours les infos
        if (getPerson(person.getEmail()) != null) {
            return this.personRepository.updatePerson(person);

        } else {
            log.error("Person not found");
        }
        return null;
    }

    @Override
    public Boolean deletePerson(String email) {
        Person person = getPerson(email);
        if (person == null) {
           return false;
        }
        this.personRepository.deletePerson(person);
        return true;
    }

    @Override
    public List<Person> getPersonsByAddress(String address) {
        return this.personRepository.getPersonsByAddress(address);
    }

    @Override
    public List<String> getEmailByCity (String city) {
        return this.personRepository.getEmailByCity(city);
    }
}