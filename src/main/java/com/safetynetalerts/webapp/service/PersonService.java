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
public class PersonService implements IPersonService{

private DataLoaderRepository dataLoaderRepository;
private PersonRepository personRepository;
private ArrayList<Person> persons;

    public PersonService() throws IOException {
        this.dataLoaderRepository = new DataLoaderRepository(new ObjectMapper());
        this.personRepository = new PersonRepository(new ArrayList<>(this.dataLoaderRepository.getResponse().getPersons()));
    }

    @Override
    public List<Person> getAllPersons(){
    return this.personRepository.getPersons();
}

    @Override
    public Person getPerson(String email) {
        return this.personRepository.getPerson(email);
    }

    @Override
    public Person savePerson(Person person){
        if(getPerson(person.getEmail()) != null){
            return null;
            //erreur 404 (spring)
        } else {
        return this.personRepository.savePerson(person);
        }
    }

    @Override
    public Person updatePerson() {
        // tricky, toujours en partant de l'email. Mettre à jours les infos
        return null;
    }

    @Override
    public boolean deletePerson() {
        // remove de l'array
        return false;
    }

}
